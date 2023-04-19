package com.mark.coinmarketapi.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.mark.coinmarketapi.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    Optional<Quote> findFirstBySourceCmcIdAndDestinationCmcIdAndLastUpdatedAfterOrderByLastUpdatedDesc(
        Integer sourceCmcId,
        Integer destinationCmcId,
        LocalDateTime lastUpdated
    );

}
