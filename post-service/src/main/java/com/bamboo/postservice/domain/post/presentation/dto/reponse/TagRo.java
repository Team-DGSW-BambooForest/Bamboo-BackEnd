package com.bamboo.postservice.domain.post.presentation.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TagRo {
    private Long tagId;

    private String hashTag;
}
