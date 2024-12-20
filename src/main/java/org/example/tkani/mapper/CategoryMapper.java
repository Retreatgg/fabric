package org.example.tkani.mapper;

import org.example.tkani.dto.CategoryDto;
import org.example.tkani.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);
    List<CategoryDto> toListDto(List<Category> categoryList);

}
