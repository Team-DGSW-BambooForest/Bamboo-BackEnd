package com.bamboo.adminservice.doamin.admin.presentation;

import com.bamboo.adminservice.doamin.admin.presentation.dto.request.AdminRequestDto;
import com.bamboo.adminservice.doamin.admin.presentation.dto.reseponse.AdminResponseDto;
import com.bamboo.adminservice.doamin.admin.service.AdminService;
import com.bamboo.adminservice.global.annotation.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @AuthToken
    @PatchMapping("/status/{post-id}")
    public AdminResponseDto postStatus(@PathVariable("post-id")Long postId,
                               @RequestBody AdminRequestDto adminRequestDto){
        adminService.postStatus(postId, adminRequestDto);
        return new AdminResponseDto(HttpStatus.OK, "성공적으로 처리되었습니다.");
    }

    @AuthToken
    @DeleteMapping("/post/delete/{post-id}")
    public void deletePost(@PathVariable("post-id") Long postId){
        adminService.deletePost(postId);
    }

    @AuthToken
    @DeleteMapping("/comment/delete/{comment-id}")
    public void deleteComment(@PathVariable("comment-id") Long postId){
        adminService.deleteComment(postId);
    }
}
