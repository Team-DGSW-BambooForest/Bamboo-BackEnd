package com.bamboo.apigatewayservice.config;

import com.bamboo.apigatewayservice.fliter.AuthorizationHeaderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    @Bean
    public RouteLocator gatewayRoute(RouteLocatorBuilder builder, AuthorizationHeaderFilter authorizationHeaderFilter){
        return builder.routes()
                .route("uesr-service", p ->
                        p.path("/user/**")
                                .filters(f -> f.filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config()))
                                        .addRequestHeader("user-data", "test"))
                                .uri("http://3.35.127.40:7000/")
                ).route("post-service", p ->
                        p.path("/post/**")
                                .filters(f -> f.filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config()))
                                        .addRequestHeader("user-data", "test"))
                                .uri("http://3.35.240.224:7001")
                ).route("admin-service", p ->
                        p.path("/admin/**")
                                .filters(f -> f.filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config()))
                                        .addRequestHeader("user-data", "test"))
                                .uri("http://3.35.240.224:7001")
                ).route("upload-service", p ->
                        p.path("/upload/**")
                                .filters(f -> f.filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config()))
                                        .addRequestHeader("user-data", "test"))
                                .uri("http://3.35.240.224:7001")
                ).route("comment-service", p ->
                        p.path("/comment/**")
                                .filters(f -> f.filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config()))
                                        .addRequestHeader("user-data", "test"))
                                .uri("http://3.35.240.224:7001")
                )
                .build();
    }
}
