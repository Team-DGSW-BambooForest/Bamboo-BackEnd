package com.bamboo.adminservice.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException{

    private final HttpStatus httpStatus;
    private String message;


}
