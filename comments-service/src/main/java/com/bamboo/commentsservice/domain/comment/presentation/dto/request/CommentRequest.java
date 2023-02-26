package com.bamboo.commentsservice.domain.comment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {

    private Long postId;

    private Long parentCommentId;

    private String content;

}
