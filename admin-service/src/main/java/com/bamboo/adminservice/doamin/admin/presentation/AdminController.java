package com.bamboo.adminservice.doamin.admin.presentation;

import com.bamboo.adminservice.global.annotation.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    @AuthToken
    @GetMapping
    public String test(@RequestAttribute String user){
        return user;
    }
}
