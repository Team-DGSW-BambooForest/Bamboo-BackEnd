package com.bamboo.adminservice.global.config;

import com.bamboo.adminservice.global.intercetor.HttpIntercetor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final HttpIntercetor httpIntercetor;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(httpIntercetor);
    }
}