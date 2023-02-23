package com.bamboo.commentsservice.domain.comment.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class CommentRequest {

    private Long postId;

    private Long commnetId;

    private String content;

}
