package com.bamboo.commentsservice.global.exception;

import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends BusinessException{
    public static final CommentNotFoundException EXCEPTION = new CommentNotFoundException();


    public CommentNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "상위 댓글을 찾을수 없습니다");
    }
}
