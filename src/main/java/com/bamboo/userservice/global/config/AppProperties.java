package com.bamboo.userservice.global.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String secret;
    private String refreshSecret;
    private String clientId;
    private String clientSecret;
    private String authUrl;
    private String openApiUrl;

}
