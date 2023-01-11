package com.bamboo.userservice.domain.auth.service;

import com.bamboo.userservice.domain.auth.presentation.dto.api.DOpenApiDto;
import com.bamboo.userservice.domain.auth.presentation.dto.request.DAuthRequestDto;
import com.bamboo.userservice.domain.auth.presentation.dto.request.LoginRequestDto;
import com.bamboo.userservice.domain.auth.presentation.dto.response.DAuthResponseDto;
import com.bamboo.userservice.domain.auth.presentation.dto.response.LoginResponseDto;
import com.bamboo.userservice.domain.user.UserEntity;
import com.bamboo.userservice.domain.user.service.UserService;
import com.bamboo.userservice.global.config.AppProperties;
import com.bamboo.userservice.global.jwt.TokenProvider;
import com.bamboo.userservice.global.resttemplate.RestTemplateConfig;
import com.bamboo.userservice.global.type.JwtAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DauthService {
    private final AppProperties appProperties;
    private final RestTemplateConfig restTemplateConfig;
    private final UserService userService;
    private final TokenProvider tokenProvider;


    //유저 정보 불어오기
    private DOpenApiDto getCodeToDodamInfo(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "
                + getDAuthToken(code).getAccessToken());
        return restTemplateConfig.openTemplate().exchange(
                "/user",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                DOpenApiDto.class
        ).getBody();
    }


    //엑세스토큰 리프레쉬토큰 불러오기
    private DAuthResponseDto getDAuthToken(String code) {
        return restTemplateConfig.authTemplate()
                .postForObject("/token", new HttpEntity<>(
                        DAuthRequestDto.builder()
                                .code(code)
                                .clientId(appProperties.getClientId())
                                .clientSecret(appProperties.getClientSecret())
                                .build()
                ), DAuthResponseDto.class);
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        DOpenApiDto data = getCodeToDodamInfo(loginRequestDto.getCode());
        UserEntity userEntity = userService.save(data);
        Integer userId = userEntity.getUserId();
        return LoginResponseDto.builder()
                .userEntity(userEntity)
                .token(tokenProvider.generateToken(userId, JwtAuth.ACCESS_TOKEN))
                .refreshToken(tokenProvider.generateToken(userId, JwtAuth.REFRESH_TOKEN))
                .build();
    }
}