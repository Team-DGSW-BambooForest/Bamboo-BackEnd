package com.bamboo.commentsservice.domain.comment.service;

import com.bamboo.commentsservice.domain.comment.domain.Comment;
import com.bamboo.commentsservice.domain.comment.domain.repository.CommentRepository;
import com.bamboo.commentsservice.domain.comment.presentation.dto.request.CommentRequest;
import com.bamboo.commentsservice.domain.comment.presentation.dto.response.CommentRo;
import com.bamboo.commentsservice.global.exception.CommentNotFoundException;
import com.bamboo.commentsservice.global.util.TimeAgoFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    private final TimeAgoFormatter timeAgoFormatter;

    @Transactional
    public ResponseEntity<?> createComment(
            CommentRequest request,
            String writer,
            String profileImage
    ) {

        if(request.getParentCommnetId() != 0) {
            commentRepository.findById(request.getParentCommnetId())
                    .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        }

        Comment comment = Comment.builder()
                .writer(writer)
                .profileImage(profileImage)
                .parentId(request.getParentCommnetId())
                .postId(request.getPostId())
                .content(request.getContent())
                .build();

        commentRepository.save(comment);

        return ResponseEntity.status(201).body("댓글이 성공적으로 게시 되었습니다");
    }

    @Transactional(readOnly = true)
    public List<CommentRo> getParentCommentByPostId(Long id) {
        List<Comment> comments = commentRepository.findParentCommentByPostId(id);
        List<CommentRo> commetList = comments.stream().map
                (it -> new CommentRo(it.getId(), it.getProfileImage(), it.getWriter(), it.getContent(), timeAgoFormatter.format(it.getCreatedAt())))
                .collect(Collectors.toList());
        return commetList;
    }

    @Transactional(readOnly = true)
    public List<CommentRo> getCommentByCommentId(Long id) {
        List<Comment> comments = commentRepository.findAllByParentId(id);
        List<CommentRo> commetList = comments.stream().map
                        (it -> new CommentRo(it.getId(), it.getProfileImage(), it.getWriter(), it.getContent(), timeAgoFormatter.format(it.getCreatedAt())))
                .collect(Collectors.toList());
        return commetList;
    }

}
