package com.basis.campina.xdocumentos.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {
    private final ApplicationProperties applicationProperties;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(applicationProperties.getUrl())
                .credentials(applicationProperties.getAccessKey(), applicationProperties.getSecretKey())
                .build();
        if (!existsBucket(minioClient, applicationProperties.getBucket())) {
            makeBucket(minioClient, applicationProperties.getBucket());
        }
        return minioClient;
    }

    @SneakyThrows
    public boolean existsBucket(MinioClient minioClient, String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    @SneakyThrows
    public void makeBucket(MinioClient minioClient, String bucketName) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
    }
}
