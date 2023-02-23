package com.bamboo.commentsservice.domain.comment.service;

import com.bamboo.commentsservice.domain.comment.domain.Comment;
import com.bamboo.commentsservice.domain.comment.domain.repository.CommentRepository;
import com.bamboo.commentsservice.domain.comment.presentation.dto.request.CommentRequest;
import com.bamboo.commentsservice.domain.comment.presentation.dto.response.CommentRo;
import com.bamboo.commentsservice.global.exception.CommentNotFoundException;
import com.bamboo.commentsservice.global.util.TimeAgoFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    private final TimeAgoFormatter timeAgoFormatter;

    public ResponseEntity<?> createComment(
            CommentRequest request,
            String writer,
            String profileImage
    ) {

        if(request.getCommnetId() != null) {
            commentRepository.findById(request.getCommnetId())
                    .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        }

        Comment comment = Comment.builder()
                .writer(writer)
                .profileImage(profileImage)
                .parentId(request.getCommnetId())
                .postId(request.getPostId())
                .content(request.getContent())
                .build();

        commentRepository.save(comment);

        return ResponseEntity.status(201).body("댓글이 성공적으로 게시 되었습니다");
    }

    public List<CommentRo> findCommentByPostId(Long id) {
        List<Comment> comments = commentRepository.findParentCommentByPostId(id);
        List<CommentRo> commetList = comments.stream().map
                (it -> new CommentRo(it.getId(), it.getWriter(), it.getContent(), timeAgoFormatter.format(it.getCreatedAt())))
                .collect(Collectors.toList());
        return commetList;
    }

    public List<CommentRo> findCommentByCommentId(Long id) {
        List<Comment> comments = commentRepository.findAllByParent(id);
        List<CommentRo> commetList = comments.stream().map
                        (it -> new CommentRo(it.getId(), it.getWriter(), it.getContent(), timeAgoFormatter.format(it.getCreatedAt())))
                .collect(Collectors.toList());
        return commetList;
    }

}
