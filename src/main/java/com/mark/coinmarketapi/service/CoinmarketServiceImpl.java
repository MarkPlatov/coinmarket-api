package com.mark.coinmarketapi.service;

import java.util.List;

import com.mark.coinmarketapi.model.Coin;
import com.mark.coinmarketapi.repository.CoinmarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinmarketServiceImpl implements CoinmarketService {

    private final CoinmarketRepository repository;

    @Override
    public List<Coin> getCheckingAccounts() {
        return repository.findAll();
    }

}
