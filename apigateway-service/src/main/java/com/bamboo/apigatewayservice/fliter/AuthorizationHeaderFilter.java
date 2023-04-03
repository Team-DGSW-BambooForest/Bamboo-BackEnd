package com.bamboo.apigatewayservice.fliter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
                ServerHttpRequest req = exchange.getRequest();

                if (Objects.requireNonNull(req.getMethod()).equals(HttpMethod.GET))
                    return chain.filter(exchange);

                if (!req.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                    return onError(exchange, "토큰이 없다", HttpStatus.UNAUTHORIZED);

                String token = Objects.requireNonNull(req.getHeaders().get(HttpHeaders.AUTHORIZATION))
                        .get(0)
                        .replace("Bearer", "")
                        .trim();

                if (!isJwtValid(token))
                    return onError(exchange, "토큰이 만료됨", HttpStatus.UNAUTHORIZED);

                req.mutate().header("Content-Type", "application/json; charset=UTF-8");
                req.mutate().header("author", jwtInfo(token, "name")).build();
                req.mutate().header("profileImage", jwtInfo(token, "profileImage")).build();
                req.mutate().header("Role", jwtInfo(token, "role")).build();
                log.info("header : " + req.getHeaders().toString());
            return chain.filter(exchange.mutate().request(req).build());
        };
    }

    private String jwtInfo(String token, String getType){
        return String.valueOf(Jwts.parser()
                .setSigningKey("sdflkjasdfiofklwenfew10u9231890u23kldsfa89u0r2kwef90we32rj32rhioewf89sdfh32ui32rfehwoifjgiosdiov8923uoiklsdnfy89weutowjkehgy8923")
                .parseClaimsJws(token)
                .getBody()
                .get(getType));
    }

    private boolean isJwtValid(String token) {
        boolean returnValue = true;

        String subject = null;
        try {
            subject = Jwts.parser()
                    .setSigningKey("sdflkjasdfiofklwenfew10u9231890u23kldsfa89u0r2kwef90we32rj32rhioewf89sdfh32ui32rfehwoifjgiosdiov8923uoiklsdnfy89weutowjkehgy8923")
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (Exception e){
            returnValue = false;
        }

        if(subject == null || subject.equals("")){
            returnValue = false;
        }
        return returnValue;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus httpStatus){
        ServerHttpResponse res = exchange.getResponse();
        res.setStatusCode(httpStatus);


        log.info(message);
        return res.setComplete();

    }


    public static class Config {

    }
}