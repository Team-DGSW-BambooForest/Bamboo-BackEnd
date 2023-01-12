package com.bamboo.postservice.presentation.dto.request;

import lombok.Getter;

@Getter
public class SearchRequest {
    private int page;

    private String keyword;
}
