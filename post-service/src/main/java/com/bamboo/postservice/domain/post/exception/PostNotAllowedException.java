package com.bamboo.postservice.domain.post.exception;

import com.bamboo.postservice.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PostNotAllowedException extends BusinessException {

    public static final PostNotAllowedException EXCEPTION = new PostNotAllowedException();

    private PostNotAllowedException() {
        super(HttpStatus.BAD_REQUEST, "허용 되지 않은 게시글 입니다");
    }

}
