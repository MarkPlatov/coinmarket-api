package com.mark.coinmarketapi.facade;

import java.util.List;

import com.mark.coinmarketapi.dto.ExchangeRateRequest;
import com.mark.coinmarketapi.dto.ExchangeRateResponse;
import com.mark.coinmarketapi.dto.externalresponse.KeyInfoResponse;
import com.mark.coinmarketapi.dto.externalresponse.QuotesLatestResponse;
import com.mark.coinmarketapi.model.Coin;


public interface CoinmarketServiceFacade {

    KeyInfoResponse keyInfo();

    void updateCoinsCache();

    ExchangeRateResponse getExchangeRate(ExchangeRateRequest request);

    List<Coin> findCoinsByNamePart(String namePart);
}
