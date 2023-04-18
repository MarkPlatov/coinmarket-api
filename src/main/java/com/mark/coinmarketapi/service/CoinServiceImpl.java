package com.mark.coinmarketapi.service;

import java.util.List;

import com.mark.coinmarketapi.model.Coin;
import com.mark.coinmarketapi.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService {

    private final CoinRepository repository;

    @Override
    public void cleanCoinsTable() {
        repository.deleteAll();
    }

    @Override
    public void saveAllCoins(List<Coin> coins) {
        repository.saveAll(coins);
    }

    @Override
    public List<Coin> findCoinsByNamePart(String namePart) {
        return repository.findByNamePart(namePart);
    }
}
