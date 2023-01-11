package com.bamboo.userservice.domain.user.presentation;

import com.bamboo.userservice.domain.user.UserEntity;
import com.bamboo.userservice.domain.user.presentation.dto.response.UserResponseDto;
import com.bamboo.userservice.global.annotations.AuthToken;
import com.bamboo.userservice.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final TokenProvider tokenProvider;

    @AuthToken
    @GetMapping
    public UserResponseDto test(@RequestAttribute UserEntity user){
        return new UserResponseDto(user);
    }
}
