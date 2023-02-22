package com.bamboo.postservice.global.exception;

import org.springframework.http.HttpStatus;

public class PostNotAllowedException extends BusinessException{

    public static final PostNotAllowedException EXCEPTION = new PostNotAllowedException();

    private PostNotAllowedException() {
        super(HttpStatus.BAD_REQUEST, "허용 되지 않은 게시글 입니다");
    }

}
