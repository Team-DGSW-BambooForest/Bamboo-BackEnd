package com.bamboo.adminservice.doamin.admin.service;

import com.bamboo.adminservice.doamin.admin.feignclient.PostFeignClient;
import com.bamboo.adminservice.doamin.admin.presentation.dto.request.AdminRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final PostFeignClient postFeignClient;

    public void postStatus(Long postId, AdminRequestDto adminRequestDto){
        postFeignClient.postStatus(postId, adminRequestDto);
    }

    public void deletePost(Long postId){
        postFeignClient.deletePost(postId);
    }

    public void deleteComment(Long commentId){
        postFeignClient.deleteComment(commentId);
    }
}
