package org.example.tkani.controller;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.SubcategoryCreateDto;
import org.example.tkani.dto.SubcategoryShowDto;
import org.example.tkani.service.SubcategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subcategories")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @PostMapping()
    public ResponseEntity<SubcategoryShowDto> createSubcategory(
            @RequestBody SubcategoryCreateDto subcategoryCreateDto
    ) {
        return ResponseEntity.ok(subcategoryService.create(subcategoryCreateDto));
    }

}
