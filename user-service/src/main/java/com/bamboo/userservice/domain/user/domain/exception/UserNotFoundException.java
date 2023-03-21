package com.bamboo.userservice.domain.user.domain.exception;

import com.bamboo.userservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.");
    }
}
