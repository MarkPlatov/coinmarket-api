package com.mark.coinmarketapi.dto.externalresponse;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mark.coinmarketapi.dto.KeyInfoDto;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KeyInfoResponse extends BasicResponse<KeyInfoDto> {
}
