package com.bamboo.postservice.domain.post.domain.exception;

import com.bamboo.postservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PostAlreadyAllowedException extends BusinessException {

    public static final PostAlreadyAllowedException EXCEPTION = new PostAlreadyAllowedException();

    private PostAlreadyAllowedException() {
        super(HttpStatus.CONFLICT, "이미 승인된 게시글입니다.");
    }
}
