package com.bamboo.postservice.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostRequest {
    @NotBlank(message = "제목을 입렵해주세요")
    private String title;
    @NotBlank(message = "내욜을 입력해주세요")
    private String content;

    private String[] hashtags;
}
