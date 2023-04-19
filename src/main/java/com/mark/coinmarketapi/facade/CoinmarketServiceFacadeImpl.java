package com.mark.coinmarketapi.facade;

import java.util.List;
import javax.transaction.Transactional;

import com.mark.coinmarketapi.dto.ExchangeRateRequest;
import com.mark.coinmarketapi.dto.ExchangeRateResponse;
import com.mark.coinmarketapi.dto.externalresponse.KeyInfoResponse;
import com.mark.coinmarketapi.dto.externalresponse.QuotesLatestResponse;
import com.mark.coinmarketapi.model.Coin;
import com.mark.coinmarketapi.model.Quote;
import com.mark.coinmarketapi.service.CoinService;
import com.mark.coinmarketapi.service.IntegrationService;
import com.mark.coinmarketapi.service.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CoinmarketServiceFacadeImpl implements CoinmarketServiceFacade {

    private final IntegrationService integrationService;
    private final CoinService coinService;
    private final QuoteService quoteService;


    @Override
    public KeyInfoResponse keyInfo() {
        return integrationService.keyInfo();
    }

    @Override
    @Transactional
    public void updateCoinsCache() {
        coinService.cleanCoinsTable();
        List<Coin> coins = integrationService.loadAllCoins();
        coinService.saveAllCoins(coins);
    }

    @Override
    public ExchangeRateResponse getExchangeRate(ExchangeRateRequest request) {
        Integer sourceCmcId = request.getSourceCmcId();
        Integer destinationCmcId = request.getDestinationCmcId();
        Quote quote = quoteService.findRecent(sourceCmcId, destinationCmcId)
            .orElseGet(() -> {
                QuotesLatestResponse response = integrationService.getQuotes(sourceCmcId, destinationCmcId);
                return quoteService.save(response, sourceCmcId, destinationCmcId);
            });
        return ExchangeRateResponse.of(quote);
    }

    @Override
    public List<Coin> findCoinsByNamePart(String namePart) {
        return coinService.findCoinsByNamePart(namePart);
    }
}