package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.CategoryCreateDto;
import org.example.tkani.dto.CategoryDto;
import org.example.tkani.mapper.CategoryMapper;
import org.example.tkani.model.Category;
import org.example.tkani.repository.CategoryRepository;
import org.example.tkani.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto create(CategoryCreateDto categoryCreateDto) {
        Category category = Category.builder()
                .name(categoryCreateDto.getName())
                .build();

        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toListDto(categories);
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
