package com.mark.coinmarketapi.dto.externalresponse;

import java.util.Map;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mark.coinmarketapi.dto.CoinDto;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuotesLatestResponse extends BasicResponse<Map<String, CoinDto>> {
}
