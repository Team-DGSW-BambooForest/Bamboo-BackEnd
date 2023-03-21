package com.bamboo.userservice.domain.user.presentation;

import com.bamboo.userservice.domain.user.domain.UserEntity;
import com.bamboo.userservice.domain.user.presentation.dto.response.UserResponseDto;
import com.bamboo.userservice.global.annotations.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @AuthToken
    @GetMapping
    public UserResponseDto findUser(@RequestAttribute UserEntity user){
        return new UserResponseDto(user);
    }
}
