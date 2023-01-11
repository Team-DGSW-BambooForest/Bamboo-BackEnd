package com.bamboo.userservice.domain.auth.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class LoginRequestDto {
    private String code;
}
