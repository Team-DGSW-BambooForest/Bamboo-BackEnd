package com.bamboo.postservice.domain.post.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequest {
    @NotBlank(message = "제목을 입렵해주세요")
    private String title;
    @NotBlank(message = "내욜을 입력해주세요")
    private String content;

    private String[] hashtags;
}
