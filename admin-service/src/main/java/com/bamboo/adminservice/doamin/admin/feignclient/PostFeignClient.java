package com.bamboo.adminservice.doamin.admin.feignclient;

import com.bamboo.adminservice.doamin.admin.presentation.dto.request.AdminRequestDto;
import com.bamboo.adminservice.global.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "PostFeignClient", url = "${post-api.url}", configuration = FeignClientConfig.class)
public interface PostFeignClient {
    @RequestMapping(method = RequestMethod.PATCH, value = "/status/{post-id}")
    void postStatus(@PathVariable("post-id") Long postId,
                                @RequestBody AdminRequestDto adminRequestDto);
}
