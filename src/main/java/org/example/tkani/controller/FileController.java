package org.example.tkani.controller;

import lombok.RequiredArgsConstructor;
import org.example.tkani.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable(name = "fileName") String fileName) {
        return fileService.getOutputFile(fileName, "/files");
    }
}
