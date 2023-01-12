package com.bamboo.postservice.presentation.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
@Builder
public class PostRo {
    private Long postId;

    private String title;

    private String content;

    private List<TagRo> hashTags;

}
