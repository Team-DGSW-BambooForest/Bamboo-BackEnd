package com.bamboo.userservice.domain.token.exception;

import com.bamboo.userservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenForgeryException extends BusinessException {
    public static final TokenForgeryException EXCEPTION = new TokenForgeryException();

    private TokenForgeryException(){
        super(HttpStatus.UNAUTHORIZED, "위조된 토큰입니다.");
    }
}
