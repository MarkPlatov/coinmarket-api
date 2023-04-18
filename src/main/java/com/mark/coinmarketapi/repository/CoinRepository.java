package com.mark.coinmarketapi.repository;

import java.util.List;

import com.mark.coinmarketapi.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CoinRepository extends JpaRepository<Coin, Integer> {
    @Query("select c from Coin c " +
        "where upper(c.name) like upper(concat('%', :namePart, '%')) " +
        "or upper(c.symbol) like upper(concat('%', :namePart, '%')) " +
        "or upper(c.slug) like upper(concat('%', :namePart, '%'))")
    List<Coin> findByNamePart(String namePart);

}
