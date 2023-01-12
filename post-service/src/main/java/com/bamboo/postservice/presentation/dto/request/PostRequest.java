package com.bamboo.postservice.presentation.dto.request;

import lombok.Getter;

@Getter
public class PostRequest {
    private String title;

    private String content;

    private String[] hashtags;
}
