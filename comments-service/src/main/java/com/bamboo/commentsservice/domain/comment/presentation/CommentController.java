package com.bamboo.commentsservice.domain.comment.presentation;

import com.bamboo.commentsservice.domain.comment.presentation.dto.response.CommentRo;
import com.bamboo.commentsservice.domain.comment.service.CommentService;
import com.bamboo.commentsservice.domain.comment.domain.Comment;
import com.bamboo.commentsservice.domain.comment.presentation.dto.request.CommentRequest;
import com.bamboo.commentsservice.global.annotation.AuthToken;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @AuthToken
    @PostMapping("/create")
    public ResponseEntity<?> createComment(
            @RequestBody CommentRequest request,
            @RequestAttribute String writer,
            @RequestAttribute String profileImage
    ) {
        return commentService.createComment(request, writer, profileImage);
    }

    @GetMapping("/{id}")
    public List<CommentRo> getCommentByPostId(@PathVariable("id") Long postId) {
        return commentService.findCommentByPostId(postId);
    }

    @GetMapping("/nested/{id}")
    public List<CommentRo> getCommentByCommentId(@PathVariable("id") Long id) {
        return commentService.findCommentByCommentId(id);
    }

}
