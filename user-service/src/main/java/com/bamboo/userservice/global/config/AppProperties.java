package com.bamboo.userservice.global.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "app")
@RefreshScope
public class AppProperties {
    private String secret;
    private String refreshSecret;
    private String clientId;
    private String clientSecret;
    private String authUrl;
    private String openApiUrl;
}
