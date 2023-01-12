package com.bamboo.postservice.service;


import com.bamboo.postservice.domain.HashTag;
import com.bamboo.postservice.domain.Post;
import com.bamboo.postservice.domain.repository.HashTagRepository;
import com.bamboo.postservice.domain.repository.PostRepository;
import com.bamboo.postservice.exception.PostNotFoundException;
import com.bamboo.postservice.presentation.dto.reponse.PostListRo;
import com.bamboo.postservice.presentation.dto.reponse.PostRo;
import com.bamboo.postservice.presentation.dto.reponse.TagRo;
import com.bamboo.postservice.presentation.dto.request.PostRequest;
import com.bamboo.postservice.presentation.dto.request.SearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;

    @Transactional
    public void creatPost(PostRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .hashTagList(new ArrayList<>())
                .build();
        postRepository.save(post);

        List<HashTag> hashTagList = Arrays.stream(request.getHashtags())
                .map(it -> HashTag.builder()
                        .hashTag(it)
                        .build())
                .collect(toList());

        hashTagRepository.saveAll(hashTagList);

        IntStream.range(0, request.getHashtags().length).mapToObj(hashTagList::get).forEach(post::addHashTag);
    }
    @Transactional(readOnly = true)
    public PostRo getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> {
                    throw PostNotFoundException.EXPECTION;
                });

        List<TagRo> hashTagList = hashTagRepository.findByPost_PostId(id)
                .stream().map(it -> new TagRo(it.getTagId(), it.getHashTag()))
                .collect(toList());

        return new PostRo(post.getPostId(), post.getTitle(), post.getContent(), hashTagList);
    }
    @Transactional(readOnly = true)
    public PostListRo getAllPost(int page) {
        Pageable pageable = PageRequest.of(page-1, 10, Sort.Direction.ASC, "postId");

        Page<Post> posts = postRepository.findAll(pageable);

        List<PostRo> postList = posts.stream().map(it ->
                    new PostRo(it.getPostId(), it.getTitle(), it.getContent(), hashTagRepository.findByPost_PostId(it.getPostId()))
                ).collect(toList());

        return postListRobulider(postList);
    }

    @Transactional(readOnly = true)
    public PostListRo getPostByTitle(SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage()-1, 4, Sort.Direction.ASC, "postId");

        Page<Post> posts = postRepository.findByTitleContaining(request.getKeyword(),pageable);

        List<PostRo> postList = posts.stream().map(it ->
                new PostRo(it.getPostId(), it.getTitle(), it.getContent(), hashTagRepository.findByPost_PostId(it.getPostId()))
                ).collect(toList());

        return postListRobulider(postList);
    }
    @Transactional(readOnly = true)
    public PostListRo getPostByHashTag(SearchRequest request) {
        List<HashTag> hashTagContaining = hashTagRepository.findByHashTagContaining(request.getKeyword());

        List<Post> posts = hashTagContaining.stream().map(it ->
                postRepository.findByPostId(it.getPost().getPostId())).distinct().collect(toList());

        List<PostRo> postList = posts.stream().map(it ->
                new PostRo(it.getPostId(), it.getTitle(), it.getContent(), hashTagRepository.findByPost_PostId(it.getPostId()))
        ).collect(toList());

        return postListRobulider(postList);
    }

    PostListRo postListRobulider(List<PostRo> postRoList) {
        return  PostListRo.builder()
                .list(postRoList)
                .build();
    }

}