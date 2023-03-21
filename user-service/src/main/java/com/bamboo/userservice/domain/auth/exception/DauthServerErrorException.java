package com.bamboo.userservice.domain.auth.exception;

import com.bamboo.userservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DauthServerErrorException extends BusinessException {
    public static final DauthServerErrorException EXCEPTION = new DauthServerErrorException();

    private DauthServerErrorException(){
        super(HttpStatus.INTERNAL_SERVER_ERROR, "서버 통신 중 오류");
    }
}
