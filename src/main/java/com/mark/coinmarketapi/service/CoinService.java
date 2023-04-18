package com.mark.coinmarketapi.service;

import java.util.List;

import com.mark.coinmarketapi.model.Coin;

public interface CoinService {
    void cleanCoinsTable();

    void saveAllCoins(List<Coin> coins);

    List<Coin> findCoinsByNamePart(String namePart);
}
