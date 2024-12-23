package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.SubcategoryCreateDto;
import org.example.tkani.dto.SubcategoryDto;
import org.example.tkani.dto.SubcategoryShowDto;
import org.example.tkani.mapper.SubcategoryMapper;
import org.example.tkani.model.Subcategory;
import org.example.tkani.repository.CategoryRepository;
import org.example.tkani.repository.SubcategoryRepository;
import org.example.tkani.service.SubcategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryMapper subcategoryMapper;

    @Override
    public List<SubcategoryDto> subcategoriesByCategoryId(Long categoryId) {
        List<Subcategory> subcategories = subcategoryRepository.findAllByCategoryId(categoryId);
        return subcategoryMapper.toListDto(subcategories);
    }

    @Override
    public SubcategoryShowDto create(SubcategoryCreateDto subcategoryCreateDto) {
        Subcategory subcategory = Subcategory.builder()
                .name(subcategoryCreateDto.getName())
                .category(categoryRepository.findById(subcategoryCreateDto.getCategoryId()).orElseThrow())
                .build();

        subcategoryRepository.save(subcategory);
        return subcategoryMapper.toShowDto(subcategory);
    }

    @Override
    public Subcategory findById(Long subcategoryId) {
        return subcategoryRepository.findById(subcategoryId).orElse(null);
    }
}
