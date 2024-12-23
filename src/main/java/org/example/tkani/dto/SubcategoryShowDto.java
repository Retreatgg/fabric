package org.example.tkani.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryShowDto {

    private Long id;
    private String name;
    private CategoryDto categoryDto;

}
