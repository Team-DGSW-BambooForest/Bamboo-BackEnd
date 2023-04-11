package com.bamboo.postservice.domain.post.presentation.dto.request;

import com.bamboo.postservice.domain.post.domain.enums.PostStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class ChangeStatusRequest {

    private PostStatus status;

}
