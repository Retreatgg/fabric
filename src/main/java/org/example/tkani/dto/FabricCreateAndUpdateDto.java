package org.example.tkani.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricCreateAndUpdateDto {

    private String name;
    private String description;
    private Double price;
    private MultipartFile image;
    private Long subcategoryId;

}
