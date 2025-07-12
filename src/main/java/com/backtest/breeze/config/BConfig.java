package com.backtest.breeze.config;

import com.breeze.breezeconnect.BreezeConnect;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@PropertySource("classpath:breeze.properties")
@Configuration
public class BConfig {

    @Value("${breeze.apiKey}")
    private String apiKey;

    @Value("${breeze.apiSecret}")
    private String apiSecret;

    @Value("${breeze.loginURL}")
    private String loginUrl;


    @Bean
    public BreezeConnect getBreezeConnect() {
        return new BreezeConnect(apiKey);
    }
}

