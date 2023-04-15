package com.mark.coinmarketapi.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteDto {

    @Schema(example = "30435.534891903102")
    private Double price;

    @Schema(example = "2023-04-15T11:13:00.000Z")
    private LocalDateTime lastUpdated;

}
