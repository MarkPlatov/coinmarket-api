package com.mark.coinmarketapi.service;

import java.util.List;

import com.mark.coinmarketapi.model.Coin;
import com.mark.coinmarketapi.repository.CoinmarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService {

    private final CoinmarketRepository repository;

    @Override
    public void cleanCoinsTable() {
        repository.deleteAll();
    }

    @Override
    public void saveAllCoins(List<Coin> coins) {
        repository.saveAll(coins);
    }

}
