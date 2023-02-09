package com.bamboo.postservice.Client;

import com.bamboo.postservice.presentation.dto.reponse.UserRo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface UserFeinClient {

    @GetMapping("/user")
    UserRo getUser() throws Exception;
}
