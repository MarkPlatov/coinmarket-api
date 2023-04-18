package com.mark.coinmarketapi.service;

import java.util.Optional;

import com.mark.coinmarketapi.dto.externalresponse.QuotesLatestResponse;
import com.mark.coinmarketapi.model.Quote;

public interface QuoteService {

    Optional<Quote> findRecent(Integer sourceCmsId, Integer destinationCmcId);

    Quote save(QuotesLatestResponse response, Integer sourceCmcId, Integer destinationCmcId);
}
