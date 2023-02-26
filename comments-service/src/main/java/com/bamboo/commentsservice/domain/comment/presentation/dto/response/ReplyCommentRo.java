package com.bamboo.commentsservice.domain.comment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class ReplyCommentRo {
    private CommentRo comment;
    private List<CommentRo> Reply;
}
