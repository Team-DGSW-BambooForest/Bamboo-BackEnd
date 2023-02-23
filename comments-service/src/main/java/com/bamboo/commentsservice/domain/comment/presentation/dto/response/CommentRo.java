package com.bamboo.commentsservice.domain.comment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRo {

    private Long commentId;

    private String writer;

    private String cotent;

    private String diffTime;
}
