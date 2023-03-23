package com.bamboo.postservice.domain.post.domain.exception;

import com.bamboo.postservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends BusinessException {
    public static final PostNotFoundException EXPECTION = new PostNotFoundException();

    private PostNotFoundException() {
        super(HttpStatus.NOT_FOUND, "게시글을 찾을수 없습니다");
    }
}
