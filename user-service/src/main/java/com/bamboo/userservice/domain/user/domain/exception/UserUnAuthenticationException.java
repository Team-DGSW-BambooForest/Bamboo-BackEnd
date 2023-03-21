package com.bamboo.userservice.domain.user.domain.exception;

import com.bamboo.userservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserUnAuthenticationException extends BusinessException {
    public static final UserUnAuthenticationException EXCEPTION = new UserUnAuthenticationException();

    public UserUnAuthenticationException() {
        super(HttpStatus.UNAUTHORIZED, "토큰이 입력되지 않았습니다.");
    }
}
