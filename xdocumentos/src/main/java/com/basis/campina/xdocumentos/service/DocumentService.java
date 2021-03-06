package com.basis.campina.xdocumentos.service;

import com.basis.campina.xdocumentos.config.ApplicationProperties;
import com.basis.campina.xdocumentos.service.dto.DocumentDTO;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class DocumentService {

    private final MinioClient minioClient;

    private final ApplicationProperties applicationProperties;

    @SneakyThrows
    public String save(DocumentDTO documentoDto){
        minioClient.putObject(PutObjectArgs.builder()
                .stream(new ByteArrayInputStream(documentoDto.getFile().getBytes()),documentoDto.getFile().getBytes().length,0)
                .contentType(StandardCharsets.UTF_8.toString())
                .bucket(applicationProperties.getBucket())
                .object(documentoDto.getUuId())
                .build());
        return documentoDto.getUuId();
    }

    @SneakyThrows
    public DocumentDTO getDocument(String uuid){
        GetObjectResponse file = minioClient.getObject(GetObjectArgs.builder()
                .bucket(applicationProperties.getBucket())
                .object(uuid)
                .build());
        return new DocumentDTO(uuid, IOUtils.toString(file, StandardCharsets.UTF_8.toString()));
    }

    @SneakyThrows
    public void deletar(String uuid){
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(applicationProperties.getBucket())
                .object(uuid)
                .build());
    }
}
