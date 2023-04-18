package com.mark.coinmarketapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "application")
public class AppConfig {

    private String exchangeRateQueue;

    private String coinQueue;

    private Long quoteExpirationTimeMinutes;

}
