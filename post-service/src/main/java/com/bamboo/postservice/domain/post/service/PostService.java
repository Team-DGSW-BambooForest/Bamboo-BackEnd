package com.bamboo.postservice.domain.post.service;

import com.bamboo.postservice.domain.post.domain.HashTag;
import com.bamboo.postservice.domain.post.domain.Post;
import com.bamboo.postservice.domain.post.domain.repository.HashTagRepository;
import com.bamboo.postservice.domain.post.domain.repository.PostRepository;
import com.bamboo.postservice.domain.post.domain.status.PostStatus;
import com.bamboo.postservice.domain.post.presentation.dto.reponse.PostListRo;
import com.bamboo.postservice.domain.post.presentation.dto.reponse.PostRo;
import com.bamboo.postservice.domain.post.presentation.dto.reponse.TagRo;
import com.bamboo.postservice.domain.post.presentation.dto.request.PostRequest;
import com.bamboo.postservice.global.exception.PostNotAllowedException;
import com.bamboo.postservice.global.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;

    @Transactional
    public ResponseEntity<?> creatPost(PostRequest request, String author, String profileImage) {

        Post post = Post.builder()
                .content(request.getContent())
                .profileImage(profileImage)
                .author(author)
                .status(PostStatus.HOLD)
                .hashTagList(new ArrayList<>())
                .build();

        List<HashTag> hashTagList = Arrays.stream(request.getHashtags())
                .map(it -> HashTag.builder()
                        .hashTag(it)
                        .build())
                .collect(toList());

        for (HashTag hashTag : hashTagList) {
            post.addHashTag(hashTag);
        }

        postRepository.save(post);

        return ResponseEntity.status(201).body("게시글이 성공적으로 게시되엇습니다");
    }
    @Transactional(readOnly = true)
    public PostRo getPostById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                    PostNotFoundException.EXPECTION
                );

        if (post.getStatus().equals(PostStatus.NOT_ALLOWED) || post.getStatus().equals(PostStatus.HOLD)) {
            throw PostNotAllowedException.EXPECTION;
        }

        List<TagRo> hashTagList = hashTagRepository.findHashTagByPost_PostId(id)
                .stream().map(it -> new TagRo(it.getTagId(), it.getHashTag()))
                .collect(toList());

        return new PostRo(post.getPostId(),post.getProfileImage(), post.getContent(),post.getCreatedAt(), hashTagList);
    }
    @Transactional(readOnly = true)
    public PostListRo getAllPost(int page) {
        Pageable pageable = PageRequest.of(page-1, 10, Sort.Direction.ASC, "postId");

        Page<Post> posts = postRepository.findAllByStatus(PostStatus.ALLOWED,pageable);

        List<PostRo> postList = posts.stream().map(it ->
                    new PostRo(it.getPostId(), it.getProfileImage(), it.getContent(), it.getCreatedAt(),hashTagRepository.findAllByPost_PostId(it.getPostId()))
                ).collect(toList());

        return postListRobulider(postList);
    }

    @Transactional(readOnly = true)
    public PostListRo getPostByHashTag(int page, String tag) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.Direction.ASC, "post_id");

        List<Long> postIds = hashTagRepository.findDistinctByHashTagContaining(tag, pageable);

        List<Post> posts = postIds.stream().map(postRepository::findByPostId)
                .filter(i -> i.getStatus().equals(PostStatus.ALLOWED))
                .collect(toList());

        List<PostRo> postList = posts.stream().map(it ->
                new PostRo(it.getPostId(), it.getProfileImage(), it.getContent(), it.getCreatedAt(), hashTagRepository.findAllByPost_PostId(it.getPostId()))
        ).collect(toList());

        return postListRobulider(postList);
    }

    @Transactional(readOnly = true)
    public PostListRo getUnauthorizedPost(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.Direction.ASC, "postId");

        Page<Post> posts = postRepository.findAllByStatus(PostStatus.HOLD, pageable);

        List<PostRo> postList = posts.stream().map(it ->
                new PostRo(it.getPostId(), it.getProfileImage(), it.getContent(), it.getCreatedAt(), hashTagRepository.findAllByPost_PostId(it.getPostId()))
        ).collect(toList());

        return postListRobulider(postList);
    }

    private PostListRo postListRobulider(List<PostRo> postRoList) {
        return  PostListRo.builder()
                .list(postRoList)
                .build();
    }

}