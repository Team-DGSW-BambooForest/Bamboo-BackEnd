package com.bamboo.userservice.domain.token.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class RefreshTokenRequestDto {
    private String refreshToken;
}
