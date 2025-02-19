package com.example.spring_backend.services;

import com.example.spring_backend.config.AwsS3BucketProperties;
import io.awspring.cloud.s3.S3Template;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsS3BucketProperties.class)
public class StorageService {
    private final S3Template s3Template;
    private final AwsS3BucketProperties awsS3BucketProperties;

    @SneakyThrows
    public String save(@NonNull final MultipartFile file) {
        final String originalFilename = file.getOriginalFilename();
        String filenameWithoutExt = "";
        String fileExtension = "";

        if (originalFilename != null) {
            int lastDotIndex = originalFilename.lastIndexOf('.');
            if (lastDotIndex > 0) {
                filenameWithoutExt = originalFilename.substring(0, lastDotIndex);
                fileExtension = originalFilename.substring(lastDotIndex);
            } else {
                filenameWithoutExt = originalFilename;
            }
            filenameWithoutExt = filenameWithoutExt.replace(" ", "_");
        }

        String timestamp = String.valueOf(System.currentTimeMillis());
        final String uniqueKey = filenameWithoutExt + "_" + UUID.randomUUID() + "_" + timestamp + fileExtension;
        final String bucketName = awsS3BucketProperties.getBucketName();

        return s3Template.upload(bucketName, uniqueKey, file.getInputStream()).getURL().toString();
    }
}
