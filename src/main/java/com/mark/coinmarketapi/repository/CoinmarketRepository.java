package com.mark.coinmarketapi.repository;

import com.mark.coinmarketapi.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinmarketRepository extends JpaRepository<Coin, Integer> {

}
