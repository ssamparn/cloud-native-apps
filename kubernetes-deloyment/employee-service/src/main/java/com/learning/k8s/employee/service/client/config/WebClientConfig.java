package com.learning.k8s.employee.service.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${address.service.url}")
    private String addressServiceUrl;

    @Bean
    public WebClient restClient() {
        return WebClient.builder()
                .baseUrl(addressServiceUrl)
                .build();
    }
}