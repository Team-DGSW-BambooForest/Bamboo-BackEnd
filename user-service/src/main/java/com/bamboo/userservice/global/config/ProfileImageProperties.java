package com.bamboo.userservice.global.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@Getter
public class ProfileImageProperties {
    @Value("${common.profile-url}")
    private String profileImage;
}
