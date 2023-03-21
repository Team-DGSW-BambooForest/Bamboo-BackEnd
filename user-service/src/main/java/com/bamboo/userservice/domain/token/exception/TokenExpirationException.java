package com.bamboo.userservice.domain.token.exception;

import com.bamboo.userservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenExpirationException extends BusinessException {
    public static final TokenExpirationException EXCEPTION = new TokenExpirationException();

    private TokenExpirationException(){
        super(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");
    }
}
