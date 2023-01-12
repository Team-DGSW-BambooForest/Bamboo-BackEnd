package com.bamboo.postservice.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String message;
}
