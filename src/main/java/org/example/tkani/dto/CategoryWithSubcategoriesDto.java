package org.example.tkani.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithSubcategoriesDto {

    private Long id;
    private String name;
    private List<SubcategoryDto> subcategories;

}
