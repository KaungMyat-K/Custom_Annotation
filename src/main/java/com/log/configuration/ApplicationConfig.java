package com.log.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {
    
    // @Value("${}")
    private String Log_API_URL;

    @Bean
    WebClient webClient(){
        return WebClient.builder()
                .baseUrl(Log_API_URL)
                .build();
    }
}
