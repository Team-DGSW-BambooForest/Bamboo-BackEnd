package com.bamboo.userservice.domain.auth.presentation;

import com.bamboo.userservice.domain.auth.presentation.dto.request.LoginRequestDto;
import com.bamboo.userservice.domain.auth.presentation.dto.response.LoginResponseDto;
import com.bamboo.userservice.domain.auth.service.DauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class DauthController {
    private final DauthService dauthService;

    @PostMapping("/login")
    public LoginResponseDto Login(@RequestBody LoginRequestDto loginRequestDto){
        return dauthService.login(loginRequestDto);
    }
}
