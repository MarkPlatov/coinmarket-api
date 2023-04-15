package com.mark.coinmarketapi.facade;

import java.util.List;
import javax.transaction.Transactional;

import com.mark.coinmarketapi.dto.response.KeyInfoResponse;
import com.mark.coinmarketapi.dto.response.QuotesLatestResponse;
import com.mark.coinmarketapi.model.Coin;
import com.mark.coinmarketapi.service.CoinService;
import com.mark.coinmarketapi.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CoinmarketServiceFacadeImpl implements CoinmarketServiceFacade {

    private final IntegrationService integrationService;
    private final CoinService coinService;


    @Override
    public KeyInfoResponse keyInfo() {
        return integrationService.keyInfo();
    }

    @Override
    public QuotesLatestResponse getQuotes(Integer cmcId, Integer convertCmcId) {
        return integrationService.getQuotes(cmcId, convertCmcId);
    }

    @Override
    @Transactional
    public void updateCoinsCache() {
        coinService.cleanCoinsTable();
        List<Coin> coins = integrationService.loadAllCoins();
        coinService.saveAllCoins(coins);
    }

}
