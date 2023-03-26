package com.bamboo.postservice.domain.post.presentation.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
@Builder
public class PostListRo {

    private boolean hasMorePage;
    private int currentPage;
    private List<PostRo> list;
}
