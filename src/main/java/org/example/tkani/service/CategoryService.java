package org.example.tkani.service;

import org.example.tkani.dto.CategoryCreateDto;
import org.example.tkani.dto.CategoryDto;
import org.example.tkani.dto.CategoryWithSubcategoriesDto;
import org.example.tkani.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryCreateDto categoryCreateDto);
    List<CategoryWithSubcategoriesDto> getAll();
    Category findById(Long categoryId);
    void deleteById(Long id);
}
