package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.CategoryCreateDto;
import org.example.tkani.dto.CategoryDto;
import org.example.tkani.dto.CategoryWithSubcategoriesDto;
import org.example.tkani.mapper.CategoryMapper;
import org.example.tkani.model.Category;
import org.example.tkani.repository.CategoryRepository;
import org.example.tkani.service.CategoryService;
import org.example.tkani.service.SubcategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryService subcategoryService;
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
    public List<CategoryWithSubcategoriesDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryWithSubcategoriesDto> categoryWithSubcategoriesDtoList = new ArrayList<>();

        for(Category category : categories) {
            CategoryWithSubcategoriesDto categoryWithSubcategoriesDto = new CategoryWithSubcategoriesDto(
                    category.getId(),
                    category.getName(),
                    subcategoryService.subcategoriesByCategoryId(category.getId())
            );
            categoryWithSubcategoriesDtoList.add(categoryWithSubcategoriesDto);
        }

        return categoryWithSubcategoriesDtoList;
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
