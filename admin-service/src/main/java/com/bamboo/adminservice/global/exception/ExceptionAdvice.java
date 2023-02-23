package com.bamboo.adminservice.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(GlobalException.class)
    protected ResponseEntity<ExceptionResponseDto> Exception(GlobalException e){
        ExceptionResponseDto responseDto = ExceptionResponseDto.builder()
                .message(e.getMessage())
                .status(e.getHttpStatus().value())
                .error(e.getHttpStatus())
                .build();
        return ResponseEntity.status(e.getHttpStatus())
                .body(responseDto);
    }
}
