package com.bamboo.userservice.global.webclient;

import com.bamboo.userservice.global.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig  {
    private final AppProperties appProperties;

    @Bean
    public WebClient authClient(){
        return WebClient.create(appProperties.getAuthUrl());
    }
    @Bean
    public WebClient openClient() {
        return WebClient.create(appProperties.getOpenApiUrl());
    }
}
