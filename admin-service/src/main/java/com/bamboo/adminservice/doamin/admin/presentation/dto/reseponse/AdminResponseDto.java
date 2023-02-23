package com.bamboo.adminservice.doamin.admin.presentation.dto.reseponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class AdminResponseDto {
    private HttpStatus httpStatus;
    private String message;
}
