package com.bamboo.postservice.domain.post.domain.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostStatus {
    ALLOWED,
    NOT_ALLOWED,
    HOLD
}
