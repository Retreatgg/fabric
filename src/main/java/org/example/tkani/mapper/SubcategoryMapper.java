package org.example.tkani.mapper;

import org.example.tkani.dto.SubcategoryDto;
import org.example.tkani.dto.SubcategoryShowDto;
import org.example.tkani.model.Subcategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface SubcategoryMapper {

    SubcategoryDto toDto(Subcategory subcategory);
    List<SubcategoryDto> toListDto(List<Subcategory> subcategories);
    SubcategoryShowDto toShowDto(Subcategory subcategory);

}
