package com.bamboo.commentsservice.domain.comment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CommentRequest {

    private Long postId;

    private Long parentCommentId;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

}
