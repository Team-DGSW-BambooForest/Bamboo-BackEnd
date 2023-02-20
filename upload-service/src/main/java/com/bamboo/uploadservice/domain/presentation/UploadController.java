package com.bamboo.uploadservice.domain.presentation;

import com.bamboo.uploadservice.domain.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadImage(
            @PathVariable("postId") Long postId,
            @RequestParam("image") MultipartFile multipartFile
    ) {
        uploadService.uploadImage(postId, multipartFile);
    }

    @GetMapping("/{postId}")
    public String getImage(
            @PathVariable("postId") Long postId
    ) {
        return "https://bamboodgsw.s3.ap-northeast-2.amazonaws.com/image/image_" + postId;
    }

}
