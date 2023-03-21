package com.bamboo.userservice.domain.auth.exception;

import com.bamboo.userservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DauthCodeBadRequestException extends BusinessException {
    public static final DauthCodeBadRequestException EXCEPTION = new DauthCodeBadRequestException();

    private DauthCodeBadRequestException() {
        super(HttpStatus.BAD_REQUEST, "변조된 code입니다.");
    }
}
