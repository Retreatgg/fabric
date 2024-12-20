package org.example.tkani.service;

import org.example.tkani.dto.CategoryCreateDto;
import org.example.tkani.dto.CategoryDto;
import org.example.tkani.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryCreateDto categoryCreateDto);
    List<CategoryDto> getAll();
    Category findById(Long categoryId);

}
