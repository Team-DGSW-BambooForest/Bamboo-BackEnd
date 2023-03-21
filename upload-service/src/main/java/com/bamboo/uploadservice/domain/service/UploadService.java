package com.bamboo.uploadservice.domain.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.bamboo.uploadservice.global.config.AWSProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadService {

    private final AmazonS3Client amazonS3Client;
    private final AWSProperties awsProperties;

    public void uploadImage(Long postId, MultipartFile file) {
        try {
            String originName = "image/image_" + postId;
            long size = file.getSize();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(size);

            amazonS3Client.putObject(
                    new PutObjectRequest(awsProperties.getBucket(), originName, file.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getImageUrl(Long postId) {
        try {
            S3Object o = amazonS3Client.getObject(new GetObjectRequest(awsProperties.getBucket(), "image/image_"+postId));
            return amazonS3Client.getUrl(awsProperties.getBucket(), "image/image_"+postId).toString();
        } catch (AmazonS3Exception e) {
            return null;
        }
    }

}
