package com.bamboo.commentsservice.domain.comment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRo {

    private Long commentId;

    private String profileImage;

    private String writer;

    private String content;

    private String diffTime;
}
