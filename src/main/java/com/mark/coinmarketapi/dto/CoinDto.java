package com.mark.coinmarketapi.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mark.coinmarketapi.enums.ListingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinDto {

    @Schema(example = "1")
    @JsonAlias("id")
    private Integer cmcId;

    @Schema(example = "Bitcoin")
    private String name;

    @Schema(example = "BTC")
    private String symbol;

    @Schema(example = "$")
    private String sign;

    @Schema(example = "bitcoin")
    private String slug;

    @Schema(example = "true")
    @JsonAlias("is_active")
    private boolean isActive;

    @Schema(example = "true")
    private boolean isFiat;

    @Schema(example = "ACTIVE")
    private ListingStatus listingStatus;

    @Schema(example = "2023-04-15T11:13:00.000Z")
    private LocalDateTime lastUpdated;

    private Map<String, QuoteDto> quote;


    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public void setIsActive(Integer active) {
        isActive = Integer.valueOf(1).equals(active);
    }

}
