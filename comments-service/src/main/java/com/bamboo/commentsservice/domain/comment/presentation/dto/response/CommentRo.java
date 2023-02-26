package com.bamboo.commentsservice.domain.comment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class CommentRo {
    private Long id;
    private String profileImage;
    private String writer;
    private String content;
    private String createdAt;
}
