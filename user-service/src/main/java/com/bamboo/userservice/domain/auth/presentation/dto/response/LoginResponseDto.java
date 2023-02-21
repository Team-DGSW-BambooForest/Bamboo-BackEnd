package com.bamboo.userservice.domain.auth.presentation.dto.response;

import com.bamboo.userservice.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private UserEntity userEntity;
    private String token;
    private String refreshToken;
}
