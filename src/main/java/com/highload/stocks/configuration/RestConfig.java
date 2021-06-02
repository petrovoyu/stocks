package com.highload.stocks.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplateBuilder restTemplate() {
        return new RestTemplateBuilder();
    }
}
