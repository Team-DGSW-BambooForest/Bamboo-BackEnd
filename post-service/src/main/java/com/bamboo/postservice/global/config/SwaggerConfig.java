package com.bamboo.postservice.global.config;

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
                .title("대대숲 post-service api")
                .version("version 1.0")
                .description("대대숲 post-service api 문서, ip : 3.36.150.248");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
