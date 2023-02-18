package com.bamboo.postservice.domain.post.presentation;

import com.bamboo.postservice.domain.post.presentation.dto.reponse.PostListRo;
import com.bamboo.postservice.global.annotation.AuthToken;
import com.bamboo.postservice.domain.post.presentation.dto.reponse.PostRo;
import com.bamboo.postservice.domain.post.presentation.dto.request.PostRequest;
import com.bamboo.postservice.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @AuthToken
    @PostMapping("/create")
    public ResponseEntity<?> creatPost(@RequestBody @Validated PostRequest request,
                                       @RequestAttribute String author)
    {
        return postService.creatPost(request, author);
    }

    @GetMapping("/{id}")
    public PostRo getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/list")
    public PostListRo getALlPost(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        return postService.getAllPost(page);
    }

    @GetMapping("/title/{title}")
    public PostListRo getPostByTitle(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @PathVariable("title") String title) {
        return postService.getPostByTitle(page, title);
    }

    @GetMapping("/tag/{tag}")
    public PostListRo getPostByHashtag(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @PathVariable("tag") String tag) {
         return postService.getPostByHashTag(page, tag);
    }

}
