package com.mark.coinmarketapi.service;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.mark.coinmarketapi.config.AppConfig;
import com.mark.coinmarketapi.dto.CoinDto;
import com.mark.coinmarketapi.dto.externalresponse.QuotesLatestResponse;
import com.mark.coinmarketapi.model.Quote;
import com.mark.coinmarketapi.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository repository;

    private final AppConfig appConfig;


    @Override
    public Optional<Quote> findRecent(Integer sourceCmsId, Integer destinationCmcId) {
        Long expirationMinutes = appConfig.getQuoteExpirationTimeMinutes();
        LocalDateTime eldestValid = LocalDateTime.now(ZoneOffset.UTC).minus(expirationMinutes, ChronoUnit.MINUTES);
        return repository.findBySourceCmcIdAndDestinationCmcIdAndLastUpdatedAfter(
            sourceCmsId,
            destinationCmcId,
            eldestValid
        );
    }

    @Override
    public Quote save(QuotesLatestResponse quotesLatestResponse, Integer sourceCmcId, Integer destinationCmcId) {
        CoinDto coinDto = quotesLatestResponse.getData().get(sourceCmcId.toString());
        Double price = coinDto.getQuote().get(destinationCmcId.toString()).getPrice();
        LocalDateTime lastUpdated = coinDto.getQuote().get(destinationCmcId.toString()).getLastUpdated();

        Quote plainQuote = new Quote();
        plainQuote.setSourceCmcId(sourceCmcId);
        plainQuote.setDestinationCmcId(destinationCmcId);
        plainQuote.setExchangeRate(price);
        plainQuote.setLastUpdated(lastUpdated);

        Quote reverseQuote = new Quote();
        reverseQuote.setSourceCmcId(destinationCmcId);
        reverseQuote.setDestinationCmcId(sourceCmcId);
        reverseQuote.setExchangeRate(1 / price);
        reverseQuote.setLastUpdated(lastUpdated);

        repository.save(reverseQuote);
        return repository.save(plainQuote);
    }
}
