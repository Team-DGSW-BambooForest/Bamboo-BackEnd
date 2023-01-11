package com.bamboo.userservice.global.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ExceptionResponseDto {
    @JsonProperty(value = "timestamp", index = 1)
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final int status;
    private final HttpStatus error;
    private final String message;
}
