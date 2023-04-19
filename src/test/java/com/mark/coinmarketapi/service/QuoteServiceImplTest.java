package com.mark.coinmarketapi.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import com.mark.coinmarketapi.config.AppConfig;
import com.mark.coinmarketapi.dto.CoinDto;
import com.mark.coinmarketapi.dto.QuoteDto;
import com.mark.coinmarketapi.dto.externalresponse.QuotesLatestResponse;
import com.mark.coinmarketapi.enums.ListingStatus;
import com.mark.coinmarketapi.model.Quote;
import com.mark.coinmarketapi.repository.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles({"devlocal"})
class QuoteServiceImplTest {


    private final LocalDateTime NOW = LocalDateTime.now();
    private final Integer SOURCE_ID = 1;
    private final Integer DESTINATION_ID = 2;
    private final Double EXCHANGE_RATE_PLAIN = 4.;
    private final Double EXCHANGE_RATE_REVERSE = 0.25;
    private final QuotesLatestResponse DEFAULT_RESPONSE = defaultQuotesResponse();
    private final Quote QUOTE_PLAIN = quotePlain();
    private final Quote QUOTE_REVERSE = quoteReverse();

    @Autowired
    private QuoteServiceImpl quoteService;

    @Autowired
    private QuoteRepository repository;

    @Autowired
    private AppConfig appConfig;


    @Test
    public void test01savePlain() {
        Quote actual = quoteService.save(DEFAULT_RESPONSE, SOURCE_ID, DESTINATION_ID);
        assertQuotesEquals(QUOTE_PLAIN, actual);
    }

    @Test
    public void test02saveReverse() {
        test01savePlain();
        Optional<Quote> actualOpt = repository.findFirstBySourceCmcIdAndDestinationCmcIdAndLastUpdatedAfterOrderByLastUpdatedDesc(
            DESTINATION_ID,
            SOURCE_ID,
            NOW.minusMinutes(appConfig.getQuoteExpirationTimeMinutes())
        );
        assertTrue(actualOpt.isPresent());

        assertQuotesEquals(QUOTE_REVERSE, actualOpt.get());
    }

    @Test
    public void test03findRecent() {
        Optional<Quote> actualOpt = quoteService.findRecent(SOURCE_ID, DESTINATION_ID);
        assertTrue(actualOpt.isPresent());
        assertQuotesEquals(QUOTE_PLAIN, actualOpt.get());
    }


    private QuotesLatestResponse defaultQuotesResponse() {
        QuoteDto quote = new QuoteDto();
        quote.setPrice(EXCHANGE_RATE_PLAIN);
        quote.setLastUpdated(NOW);

        Map<String, QuoteDto> quotes = Map.of(DESTINATION_ID.toString(), quote);

        CoinDto coin = new CoinDto();
        coin.setName("Bitcoin");
        coin.setSymbol("BTC");
        coin.setSign("$");
        coin.setSlug("bitcoin");
        coin.setIsActive(true);
        coin.setFiat(true);
        coin.setListingStatus(ListingStatus.ACTIVE);
        coin.setLastUpdated(LocalDateTime.now());
        coin.setQuote(quotes);

        Map<String, CoinDto> data = Map.of(SOURCE_ID.toString(), coin);

        QuotesLatestResponse response = new QuotesLatestResponse();
        response.setStatus(null);
        response.setData(data);
        return response;
    }

    private Quote quotePlain() {
        Quote quote = new Quote();
        quote.setSourceCmcId(SOURCE_ID);
        quote.setDestinationCmcId(DESTINATION_ID);
        quote.setExchangeRate(EXCHANGE_RATE_PLAIN);
        quote.setLastUpdated(NOW);
        return quote;
    }

    private Quote quoteReverse() {
        Quote quote = new Quote();
        quote.setSourceCmcId(DESTINATION_ID);
        quote.setDestinationCmcId(SOURCE_ID);
        quote.setExchangeRate(EXCHANGE_RATE_REVERSE);
        quote.setLastUpdated(NOW);
        return quote;
    }


    private void assertQuotesEquals(Quote expected, Quote actual) {
        assertEquals(expected.getSourceCmcId(), actual.getSourceCmcId());
        assertEquals(expected.getDestinationCmcId(), actual.getDestinationCmcId());
        assertEquals(expected.getExchangeRate(), actual.getExchangeRate());
    }
}