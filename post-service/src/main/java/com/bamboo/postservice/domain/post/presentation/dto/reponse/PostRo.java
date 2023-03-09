package com.bamboo.postservice.domain.post.presentation.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Getter
@Builder
public class PostRo {
    private Long postId;

    private String author;

    private String profileImage;

    private String content;

    private LocalDateTime createdAt;

}
