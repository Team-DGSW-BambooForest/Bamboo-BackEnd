package com.bamboo.commentsservice.global.exception;

import org.springframework.http.HttpStatus;

public class PostCommentNotFoundException extends BusinessException{
    public PostCommentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 게시판에 댓글이 존재하지 않습니다.");
    }
}
