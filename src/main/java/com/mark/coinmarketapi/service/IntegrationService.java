package com.mark.coinmarketapi.service;

import java.util.List;

import com.mark.coinmarketapi.dto.externalresponse.KeyInfoResponse;
import com.mark.coinmarketapi.dto.externalresponse.QuotesLatestResponse;
import com.mark.coinmarketapi.model.Coin;

public interface IntegrationService {

    KeyInfoResponse keyInfo();

    QuotesLatestResponse getQuotes(Integer cmcId, Integer convertCmcId);

    List<Coin> loadAllCoins();

}
