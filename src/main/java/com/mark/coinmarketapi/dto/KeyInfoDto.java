package com.mark.coinmarketapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyInfoDto {

    private Plan plan;
    private Usage usage;

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Plan {
        private Integer creditLimitMonthly;
        private String creditLimitMonthlyReset;
        private String creditLimitMonthlyResetTimestamp;
        private Integer rateLimitMinute;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Usage {
        private Current currentMinute;
        private Current currentDay;
        private Current currentMonth;

    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Current {
        @JsonAlias("credits_used")
        private Integer requestsMade;

        @JsonAlias("credits_left")
        private Integer requestsLeft;
    }
}
