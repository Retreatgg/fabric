package org.example.tkani.service;

import org.example.tkani.dto.SubcategoryCreateDto;
import org.example.tkani.dto.SubcategoryDto;
import org.example.tkani.dto.SubcategoryShowDto;
import org.example.tkani.model.Subcategory;

import java.util.List;

public interface SubcategoryService {

    List<SubcategoryDto> subcategoriesByCategoryId(Long categoryId);
    SubcategoryShowDto create(SubcategoryCreateDto subcategoryCreateDto);
    Subcategory findById(Long subcategoryId);

}
