package com.mark.coinmarketapi.dto.response;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mark.coinmarketapi.dto.CoinDto;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CryptocurrencyMapResponse extends BasicResponse<List<CoinDto>> {
}
