package com.mark.coinmarketapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import com.mark.coinmarketapi.client.CoinmarketcapClient;
import com.mark.coinmarketapi.dto.CoinDto;
import com.mark.coinmarketapi.dto.externalresponse.KeyInfoResponse;
import com.mark.coinmarketapi.dto.externalresponse.QuotesLatestResponse;
import com.mark.coinmarketapi.enums.ListingStatus;
import com.mark.coinmarketapi.model.Coin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService {

    private final static String ADDITIONAL_COMMA_SEPERATED_FIELDS = "is_active";
    private final static int DEFAULT_STEP_SIZE = 5000;
    private final CoinmarketcapClient client;


    @Override
    public KeyInfoResponse keyInfo() {
        return client.keyInfo();
    }

    @Override
    public QuotesLatestResponse getQuotes(Integer cmcId, Integer convertCmcId) {
        boolean skipInvalid = true;
        return client.quotesLatest(
            cmcId,
            convertCmcId,
            ADDITIONAL_COMMA_SEPERATED_FIELDS,
            skipInvalid
        );
    }

    @Override
    public List<Coin> loadAllCoins() {
        List<Coin> coins = new ArrayList<>(DEFAULT_STEP_SIZE);
        coins.addAll(fiatMap(coinLambda(), ListingStatus.ACTIVE));
        coins.addAll(fiatMap(coinLambda(), ListingStatus.INACTIVE));
        coins.addAll(fiatMap(coinLambda(), ListingStatus.UNTRACKED));
        coins.addAll(fiatMap(fiatLambda(), null));
        return coins;
    }

    private List<Coin> fiatMap(
        BiFunction<ListingStatus, Integer, List<CoinDto>> integrationMethod,
        ListingStatus listingStatus
    ) {
        boolean canLoadMore = true;
        List<CoinDto> dtos = new ArrayList<>(DEFAULT_STEP_SIZE);
        for (int start = 1; canLoadMore; start += DEFAULT_STEP_SIZE) {
            List<CoinDto> data = integrationMethod.apply(listingStatus, start);
            dtos.addAll(data);
            canLoadMore = data.size() == DEFAULT_STEP_SIZE;
        }
        return dtos.stream()
            .map(dto -> {
                dto.setListingStatus(listingStatus);
                dto.setFiat(listingStatus == null);
                return Coin.of(dto);
            })
            .toList();
    }

    private BiFunction<ListingStatus, Integer, List<CoinDto>> fiatLambda() {
        boolean includeMetals = true;
        return (ignored, start) -> client.fiatMap(
            includeMetals,
            start,
            DEFAULT_STEP_SIZE
        ).getData();
    }

    private BiFunction<ListingStatus, Integer, List<CoinDto>> coinLambda() {
        return (listingStatus, start) -> client.cryptocurrencyMap(
            listingStatus.getValue(),
            ADDITIONAL_COMMA_SEPERATED_FIELDS,
            start,
            DEFAULT_STEP_SIZE
        ).getData();
    }


}
