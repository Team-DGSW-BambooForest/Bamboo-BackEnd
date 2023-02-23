package com.bamboo.adminservice.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("대대숲 admin-service api")
                .version("version 1.0")
                .description("대대숲 admin-service api 문서, ip : 54.180.217.199");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
