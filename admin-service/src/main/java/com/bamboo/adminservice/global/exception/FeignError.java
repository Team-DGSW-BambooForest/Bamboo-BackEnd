package com.bamboo.adminservice.global.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new GlobalException(HttpStatus.NOT_FOUND, "게시판이 존재하지 않습니다.");
        }
        else if (response.status() == 409) {
            return new GlobalException(HttpStatus.CONFLICT, "이미 승인된 게시판입니다.");
        }
        return null;
    }
}
