package com.bamboo.userservice.domain.token.exception;

import com.bamboo.userservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenInternalServerErrorException extends BusinessException {
    public static final TokenInternalServerErrorException EXCEPTION = new TokenInternalServerErrorException();

    private TokenInternalServerErrorException(){
        super(HttpStatus.INTERNAL_SERVER_ERROR, "토큰 서비스와의 오류가 발생하였습니다.");
    }
}
