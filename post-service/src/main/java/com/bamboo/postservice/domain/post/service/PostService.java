package com.bamboo.postservice.domain.post.service;

import com.bamboo.postservice.domain.post.domain.Post;
import com.bamboo.postservice.domain.post.domain.repository.PostRepository;
import com.bamboo.postservice.domain.post.domain.status.PostStatus;
import com.bamboo.postservice.domain.post.exception.PostEmptyException;
import com.bamboo.postservice.domain.post.presentation.dto.reponse.PostListRo;
import com.bamboo.postservice.domain.post.presentation.dto.reponse.PostRo;
import com.bamboo.postservice.domain.post.presentation.dto.request.PostRequest;
import com.bamboo.postservice.domain.post.exception.PostAlreadyAllowedException;
import com.bamboo.postservice.domain.post.exception.PostNotAllowedException;
import com.bamboo.postservice.domain.post.exception.PostNotFoundException;
import com.bamboo.postservice.global.util.PostListBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final PostListBuilder postListBuilder;

    @Transactional
    public ResponseEntity<?> creatPost(PostRequest request, String author, String profileImage) {

        Post post = Post.builder()
                .content(request.getContent())
                .profileImage(profileImage)
                .author(author)
                .status(PostStatus.HOLD)
                .build();

        postRepository.save(post);

        return ResponseEntity.status(201).body(post.getPostId());
    }
    @Transactional(readOnly = true)
    public PostRo getPostById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                    PostNotFoundException.EXPECTION
                );

        if (post.getStatus().equals(PostStatus.NOT_ALLOWED) || post.getStatus().equals(PostStatus.HOLD)) {
            throw PostNotAllowedException.EXCEPTION;
        }

        return new PostRo(post.getPostId(), post.getAuthor(), post.getProfileImage(), post.getContent(), post.getCreatedAt());
    }
    @Transactional(readOnly = true)
    public PostListRo getAllPost(int page) {
        Pageable pageable = PageRequest.of(page-1, 10, Sort.Direction.DESC, "postId");

        Page<Post> posts = postRepository.findAllByStatus(PostStatus.ALLOWED,pageable);

        if(posts.isEmpty())
            throw PostEmptyException.EXCEPTION;

        return postListBuilder.Builder(posts);
    }

    public PostListRo getPostByword(int page, String word) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.Direction.DESC, "postId");

        Page<Post> posts = postRepository.findAllByContentContainingAndStatus(word, PostStatus.ALLOWED, pageable);

        return postListBuilder.Builder(posts);
    }

    @Transactional(readOnly = true)
    public PostListRo getUnauthorizedPost(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.Direction.ASC, "postId");

        Page<Post> posts = postRepository.findAllByStatus(PostStatus.HOLD, pageable);

        return postListBuilder.Builder(posts);
    }

    @Transactional
    public void changeStatus(Long postId, PostStatus status) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXPECTION);

        if(post.getStatus().equals(PostStatus.ALLOWED)) {
            throw PostAlreadyAllowedException.EXCEPTION;
        }

        post.setStatus(status);
        postRepository.save(post);
    }

}