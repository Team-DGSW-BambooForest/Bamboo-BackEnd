package com.bamboo.uploadservice.domain.service;

import com.amazonaws.services.s3.model.*;
import com.bamboo.uploadservice.global.config.AWSConfiguration;
import com.bamboo.uploadservice.global.config.AWSProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {

    private final AWSConfiguration aws;
    private final AWSProperties awsProperties;

    public void uploadImage(Long postId, MultipartFile file) {
        try {
            String originName = "image/image_" + postId;
            long size = file.getSize();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(size);

            aws.amazonS3Client().putObject(
                    new PutObjectRequest(awsProperties.getBucket(), originName, file.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getImageUrl(Long postId) {
        try (S3Object s3Object = aws.amazonS3Client().getObject(awsProperties.getBucket(), "image/image_" + postId)) {
            return awsProperties.getUrl() + s3Object.getKey();
        } catch (AmazonS3Exception | IOException e) {
            return null;
        }
    }

}
