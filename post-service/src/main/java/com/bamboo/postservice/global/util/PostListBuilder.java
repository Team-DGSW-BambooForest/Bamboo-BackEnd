package com.bamboo.postservice.global.util;

import com.bamboo.postservice.domain.post.domain.Post;
import com.bamboo.postservice.domain.post.presentation.dto.reponse.PostListRo;
import com.bamboo.postservice.domain.post.presentation.dto.reponse.PostRo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


import static java.util.stream.Collectors.toList;

@Component
public class PostListBuilder {

    public PostListRo Builder(Page<Post> posts) {

        return PostListRo.builder()
                .hasMorePage(posts.hasNext())
                .currentPage(posts.getNumber() + 1)
                .list(posts.stream().map(it ->
                        new PostRo(it.getPostId(), it.getAuthor(),it.getProfileImage(), it.getContent(), it.getCreatedAt())
                ).collect(toList()))
                .build();

    }

}

