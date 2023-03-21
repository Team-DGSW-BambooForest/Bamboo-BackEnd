package com.bamboo.userservice.domain.auth.service;

import com.bamboo.userservice.domain.auth.exception.DauthCodeBadRequestException;
import com.bamboo.userservice.domain.auth.exception.DauthServerErrorException;
import com.bamboo.userservice.domain.auth.presentation.dto.api.DOpenApiDto;
import com.bamboo.userservice.domain.auth.presentation.dto.request.DAuthRequestDto;
import com.bamboo.userservice.domain.auth.presentation.dto.request.LoginRequestDto;
import com.bamboo.userservice.domain.auth.presentation.dto.response.DAuthResponseDto;
import com.bamboo.userservice.domain.auth.presentation.dto.response.LoginResponseDto;
import com.bamboo.userservice.domain.user.domain.UserEntity;
import com.bamboo.userservice.domain.user.domain.type.Role;
import com.bamboo.userservice.domain.user.service.UserService;
import com.bamboo.userservice.global.config.AppProperties;
import com.bamboo.userservice.global.enums.JwtType;
import com.bamboo.userservice.global.jwt.TokenProvider;
import com.bamboo.userservice.global.webclient.WebClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppProperties appProperties;
    private final WebClientConfig webClientConfig;
    private final UserService userService;
    private final TokenProvider tokenProvider;


    private Mono<DOpenApiDto> getCodeToDodamInfo(String code) {
        return getDauthToken(code)
                .map(DAuthResponseDto::getAccessToken)
                .flatMap(token -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setBearerAuth(token);
                    return webClientConfig.openClient()
                            .get()
                            .uri("/user")
                            .headers(httpHeaders -> httpHeaders.addAll(headers))
                            .retrieve()
                            .bodyToMono(DOpenApiDto.class);
                });
    }


    private Mono<DAuthResponseDto> getDauthToken(String code) {
        DAuthRequestDto requestDto = DAuthRequestDto.builder()
                .code(code)
                .clientId(appProperties.getClientId())
                .clientSecret(appProperties.getClientSecret())
                .build();

        return webClientConfig.authClient()
                .post()
                .uri("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchangeToMono(response -> {
                            if(response.statusCode().equals(HttpStatus.OK)) {
                                return response.bodyToMono(DAuthResponseDto.class);
                            }
                            else if(response.statusCode().equals(HttpStatus.FORBIDDEN)){
                                throw DauthCodeBadRequestException.EXCEPTION;
                            }
                            else {
                                throw DauthServerErrorException.EXCEPTION;
                            }
                        });
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        DOpenApiDto data = getCodeToDodamInfo(loginRequestDto.getCode()).block();
        UserEntity userEntity = userService.save(Objects.requireNonNull(data));
        String name = userEntity.getName();
        String profileImage = userEntity.getProfileImage();
        Role role = userEntity.getRole();
        return LoginResponseDto.builder()
                .userEntity(userEntity)
                .token(tokenProvider.generateToken(name, profileImage, role, JwtType.ACCESS_TOKEN))
                .refreshToken(tokenProvider.generateToken(name, profileImage, role, JwtType.REFRESH_TOKEN))
                .build();
    }
}