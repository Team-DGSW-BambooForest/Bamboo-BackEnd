package com.bamboo.postservice.presentation;

import com.bamboo.postservice.presentation.dto.reponse.PostListRo;
import com.bamboo.postservice.presentation.dto.reponse.PostRo;
import com.bamboo.postservice.presentation.dto.request.PostRequest;
import com.bamboo.postservice.presentation.dto.request.SearchRequest;
import com.bamboo.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("info")
    public String info(@Value("${server.port}") String port) {
        return "Post Service Port : " + port;
    }

    @PostMapping("/post")
    public void creatPost(@RequestBody PostRequest request) {
        postService.creatPost(request);
    }

    @GetMapping("/{id}")
    public PostRo getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/list")
    public PostListRo getALlPost(@RequestParam("page") Integer page) {
        return postService.getAllPost(page);
    }

    @GetMapping("/title")
    public PostListRo getPostByTitle(@RequestBody SearchRequest request) {
        return postService.getPostByTitle(request);
    }

    @GetMapping("/tag")
    public PostListRo getPostByHashtag(@RequestBody SearchRequest request) {
         return postService.getPostByHashTag(request);
    }

}
