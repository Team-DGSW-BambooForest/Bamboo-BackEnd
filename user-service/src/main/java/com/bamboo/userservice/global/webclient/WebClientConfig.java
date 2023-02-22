package com.bamboo.userservice.global.webclient;

import com.bamboo.userservice.global.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig  implements WebFluxConfigurer {
    private final AppProperties appProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

    @Bean
    public WebClient authClient(){
        return WebClient.create(appProperties.getAuthUrl());
    }
    @Bean
    public WebClient openClient() {
        return WebClient.create(appProperties.getOpenApiUrl());
    }
}
