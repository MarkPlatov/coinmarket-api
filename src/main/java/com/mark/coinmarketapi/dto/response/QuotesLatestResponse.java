package com.mark.coinmarketapi.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mark.coinmarketapi.dto.CoinDto;

import java.util.Map;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuotesLatestResponse extends BasicResponse<Map<String, CoinDto>> {}
