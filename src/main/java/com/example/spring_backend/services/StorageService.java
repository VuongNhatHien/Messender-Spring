package com.example.spring_backend.services;

import com.example.spring_backend.config.AwsS3BucketProperties;
import io.awspring.cloud.s3.S3Template;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsS3BucketProperties.class)
public class StorageService {
    private final S3Template s3Template;
    private final AwsS3BucketProperties awsS3BucketProperties;

    @SneakyThrows
    public String save(@NonNull final File file) {
        String originalFilename = file.getName();
        String filenameWithoutExt = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
        String fileExtension = originalFilename.contains(".") ? originalFilename.substring(originalFilename.lastIndexOf('.')) : "";

        filenameWithoutExt = filenameWithoutExt.replace(" ", "_");
        String uniqueKey = filenameWithoutExt + "_" + UUID.randomUUID() + "_" + System.currentTimeMillis() + fileExtension;

        FileInputStream inputStream = new FileInputStream(file);
        return s3Template.upload(awsS3BucketProperties.getBucketName(), uniqueKey, inputStream).getURL().toString();

    }
}
