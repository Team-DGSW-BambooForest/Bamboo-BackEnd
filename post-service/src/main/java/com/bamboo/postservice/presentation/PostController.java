package com.bamboo.postservice.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post-service")
public class PostController {

    @GetMapping("info")
    public String info(@Value("${server.port}") String port) {
        return "Post Service Port : " + port;
    }


}
