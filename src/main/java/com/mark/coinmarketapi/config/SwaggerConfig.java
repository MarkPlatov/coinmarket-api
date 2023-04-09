package com.mark.coinmarketapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    protected static final String TITLE = "Coinmarketcap Api";
    protected static final String DESCRIPTION = "Service for integration with coinmarketcap.com API";
    protected static final String VERSION = "0.0.1-SNAPSHOT";

    private Info getInfo() {
        return new Info()
                .title(TITLE)
                .version(VERSION)
                .description(DESCRIPTION);
    }

    @Bean
    public OpenAPI apiNoauth() {
        return new OpenAPI().info(getInfo());
    }

}
