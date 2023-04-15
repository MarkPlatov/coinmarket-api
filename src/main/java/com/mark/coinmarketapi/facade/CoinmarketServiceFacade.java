package com.mark.coinmarketapi.facade;

import com.mark.coinmarketapi.dto.response.KeyInfoResponse;
import com.mark.coinmarketapi.dto.response.QuotesLatestResponse;


public interface CoinmarketServiceFacade {

    KeyInfoResponse keyInfo();

    QuotesLatestResponse getQuotes(Integer cmcId, Integer convertCmcId);

    void updateCoinsCache();

}
