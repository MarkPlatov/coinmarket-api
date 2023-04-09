package com.mark.coinmarketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
public class CoinmarketApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinmarketApiApplication.class, args);
    }

}
