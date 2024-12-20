package org.example.tkani.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricDto {

    private Long id;
    private String name;
    private String description;
    private String price;
    private String imageUrl;

}
