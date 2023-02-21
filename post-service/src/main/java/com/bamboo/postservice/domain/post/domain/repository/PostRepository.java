package com.bamboo.postservice.domain.post.domain.repository;

import com.bamboo.postservice.domain.post.domain.Post;
import com.bamboo.postservice.domain.post.domain.status.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(long id);

    Page<Post> findAllByStatus(PostStatus status, Pageable pageable);

}
