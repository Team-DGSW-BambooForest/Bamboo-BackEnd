package com.bamboo.postservice.global.client;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@Getter
public class ConfigProperties  {

    @Value("${jwt.access-secret}")
    private String accessKey;

    @Value("${jwt.refresh-secret}")
    private String refreshKey;

    @Value("${anonymous.profile-url}")
    private String profileUrl;

}