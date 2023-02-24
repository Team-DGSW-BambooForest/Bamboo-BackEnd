package com.bamboo.commentsservice.domain.comment.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@NoArgsConstructor
public class CommentRequest {

    private Long postId;

    private Long parentCommnetId;

    private String content;

}
