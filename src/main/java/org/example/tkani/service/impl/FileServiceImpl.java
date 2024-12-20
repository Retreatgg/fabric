package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.tkani.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final static String UPLOAD_DIR = "data";

    @SneakyThrows
    @Override
    public String saveUploadedFile(MultipartFile file, String subDir) {
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get(UPLOAD_DIR + "/" + subDir);
        Files.createDirectories(pathDir);

        Path filePath = Paths.get(pathDir + "/" + resultFileName);
        if(!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try(OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e){
            log.error(e.getMessage());
        }

        return resultFileName;
    }

    @Override
    public ResponseEntity<InputStreamResource> getOutputFile(String fileName, String subDir) {
        try {
            Path path = Paths.get(UPLOAD_DIR + subDir + "/" + fileName);
            InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
            MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(path));
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(new InputStreamResource(resource.getInputStream()));
        } catch (IOException e) {
            log.error("No file found:", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
