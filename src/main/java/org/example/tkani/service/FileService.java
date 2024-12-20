package org.example.tkani.service;

import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    @SneakyThrows
    String saveUploadedFile(MultipartFile file, String subDir);
    ResponseEntity<InputStreamResource> getOutputFile(String fileName, String subDir);
}
