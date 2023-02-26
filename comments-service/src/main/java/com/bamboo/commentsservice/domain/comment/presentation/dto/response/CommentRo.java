package com.bamboo.commentsservice.domain.comment.presentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentRo {
    private Long id;
    private String profileImage;
    private String writer;
    private String content;
    private String createdAt;
    private List<ChildCommentRo> childComments;

    public CommentRo(Long id, String profileImage, String writer, String content, String createdAt) {
        this.id = id;
        this.profileImage = profileImage;
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
        this.childComments = new ArrayList<>();
    }

    public void addChildComment(ChildCommentRo childComment) {
        this.childComments.add(childComment);
    }
}
