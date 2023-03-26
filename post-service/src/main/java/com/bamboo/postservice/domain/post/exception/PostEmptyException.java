package com.bamboo.postservice.domain.post.exception;

import com.bamboo.postservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PostEmptyException extends BusinessException {

    public static final PostEmptyException EXCEPTION = new PostEmptyException();

    private PostEmptyException() {
        super(HttpStatus.NOT_FOUND, "게시글 리스트가 비어있습니다.");
    }
}
