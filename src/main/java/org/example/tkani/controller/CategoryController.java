package org.example.tkani.controller;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.CategoryCreateDto;
import org.example.tkani.dto.CategoryDto;
import org.example.tkani.dto.CategoryWithSubcategoriesDto;
import org.example.tkani.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryCreateDto categoryCreateDto
    ) {
        return ResponseEntity.ok(categoryService.create(categoryCreateDto));
    }

    @GetMapping()
    public ResponseEntity<List<CategoryWithSubcategoriesDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
