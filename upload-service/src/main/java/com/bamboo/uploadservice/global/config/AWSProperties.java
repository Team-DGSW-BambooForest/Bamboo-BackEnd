package com.bamboo.uploadservice.global.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "aws")
@RefreshScope
public class AWSProperties {

    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;

}
