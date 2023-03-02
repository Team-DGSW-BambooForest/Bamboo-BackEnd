package com.bamboo.commentsservice.domain.comment.service;

import com.bamboo.commentsservice.domain.comment.domain.Comment;
import com.bamboo.commentsservice.domain.comment.domain.repository.CommentRepository;
import com.bamboo.commentsservice.domain.comment.presentation.dto.request.CommentRequest;
import com.bamboo.commentsservice.domain.comment.presentation.dto.response.CommentRo;
import com.bamboo.commentsservice.domain.comment.presentation.dto.response.ReplyCommentRo;
import com.bamboo.commentsservice.global.exception.CommentNotFoundException;
import com.bamboo.commentsservice.global.exception.PostCommentNotFoundException;
import com.bamboo.commentsservice.global.util.TimeAgoFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

        if(request.getParentCommentId() != 0) {
            commentRepository.findById(request.getParentCommentId())
                    .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        }

        Comment comment = Comment.builder()
                .writer(writer)
                .profileImage(profileImage)
                .parentId(request.getParentCommentId())
                .postId(request.getPostId())
                .content(request.getContent())
                .build();

        commentRepository.save(comment);

        return ResponseEntity.status(201).body("댓글이 성공적으로 게시 되었습니다");
    }

    @Transactional(readOnly = true)
    public Integer getCommentCountByPostId(Long id) {
        return commentRepository.countAllByPostId(id);
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

    @Transactional(readOnly = true)
    public List<ReplyCommentRo> getCommentsByPostId(Long postId) {
        List<Comment> parentComments = commentRepository.findParentCommentByPostId(postId);
        if(parentComments.toString().equals("[]")) throw new PostCommentNotFoundException();

        List<ReplyCommentRo> fullComments = new ArrayList<>();
        for (Comment parentComment : parentComments) {
            CommentRo parentCommentRo = new CommentRo(parentComment.getId(), parentComment.getProfileImage(),
                    parentComment.getWriter(), parentComment.getContent(), timeAgoFormatter.format(parentComment.getCreatedAt()));

            List<Comment> childComments = commentRepository.findAllByParentId(parentComment.getId());
            List<CommentRo> childCommentRos = childComments.stream().map(childComment -> new CommentRo(childComment.getId(),
                    childComment.getProfileImage(), childComment.getWriter(), childComment.getContent(),
                    timeAgoFormatter.format(childComment.getCreatedAt()))).collect(Collectors.toList());

            ReplyCommentRo fullComment = new ReplyCommentRo(parentCommentRo, childCommentRos);
            fullComments.add(fullComment);
        }
        return fullComments;
    }
}
