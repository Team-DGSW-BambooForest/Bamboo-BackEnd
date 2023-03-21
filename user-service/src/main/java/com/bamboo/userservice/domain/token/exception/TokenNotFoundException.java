package com.bamboo.userservice.domain.token.exception;

import com.bamboo.userservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenNotFoundException extends BusinessException {
    public static final TokenNotFoundException EXCEPTION = new TokenNotFoundException();

    private TokenNotFoundException(){
        super(HttpStatus.NOT_FOUND, "토큰이 존재하지 않습니다.");
    }
}
