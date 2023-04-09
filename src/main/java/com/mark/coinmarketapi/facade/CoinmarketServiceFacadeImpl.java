package com.mark.coinmarketapi.facade;

import java.util.List;

import com.mark.coinmarketapi.model.Coin;
import com.mark.coinmarketapi.service.CoinmarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CoinmarketServiceFacadeImpl implements CoinmarketServiceFacade {

    private final CoinmarketService coinmarketService;

    @Override
    public List<Coin> getCoin() {
        return coinmarketService.getCheckingAccounts();
    }

}
