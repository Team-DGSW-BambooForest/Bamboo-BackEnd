package com.bamboo.userservice.global.resttemplate;

import com.bamboo.userservice.global.type.EndPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate(final String endpoint) {
        return restTemplateBuilder.rootUri(endpoint)
                .additionalInterceptors(new RestTemplateRequestInterceptor())
                .errorHandler(new RestTemplateErrorHandler())
                .setConnectTimeout(Duration.ofMinutes(2))
                .build();
    }

    @Bean
    public RestTemplate authTemplate() {
        return restTemplate(EndPoint.AUTH.getEndPoint());
    }

    @Bean
    public RestTemplate openTemplate() {
        return restTemplate(EndPoint.OPEN_API.getEndPoint());
    }
}
