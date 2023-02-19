package com.bamboo.userservice.global.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Getter
@Component
@RefreshScope
public class JwtProperties {

    @Value("${jwt.access-expire}")
    private long JWT_ACCESS_EXPIRE;
    @Value("${jwt.refresh-expire}")
    private long JWT_REFRESH_EXPIRE;

}
