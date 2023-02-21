package com.bamboo.userservice.domain.token.presentation;

import com.bamboo.userservice.domain.token.presentation.dto.request.RefreshTokenRequestDto;
import com.bamboo.userservice.domain.token.presentation.dto.response.AccessTokenResponseDto;
import com.bamboo.userservice.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class TokenController {

    private final TokenProvider tokenProvider;

    @PostMapping("/token/refresh")
    public AccessTokenResponseDto refresh(@RequestBody RefreshTokenRequestDto requestDto){
        return new AccessTokenResponseDto(tokenProvider.refreshToken(requestDto.getRefreshToken()));
    }
}
