package com.mark.coinmarketapi.dto;

import java.time.LocalDateTime;

import com.mark.coinmarketapi.model.Quote;
import lombok.Data;

@Data
public class ExchangeRateResponse {

    private Integer sourceCmcId;
    private Integer destinationCmcId;
    private Double exchangeRate;
    private LocalDateTime lastUpdated;

    public static ExchangeRateResponse of(Quote quote) {
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setSourceCmcId(quote.getSourceCmcId());
        response.setDestinationCmcId(quote.getDestinationCmcId());
        response.setExchangeRate(quote.getExchangeRate());
        response.setLastUpdated(quote.getLastUpdated());
        return response;
    }

}
