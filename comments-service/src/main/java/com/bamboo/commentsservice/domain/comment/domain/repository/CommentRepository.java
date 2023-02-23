package com.bamboo.commentsservice.domain.comment.domain.repository;

import com.bamboo.commentsservice.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from `bamboo`.`comment` as comment where comment.parentId is null and comment.post_id = ?", nativeQuery = true)
    List<Comment> findParentCommentByPostId(Long id);

    List<Comment> findAllByParent(Long id);

}