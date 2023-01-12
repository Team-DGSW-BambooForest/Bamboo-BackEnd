package com.bamboo.postservice.presentation.dto.reponse;

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
