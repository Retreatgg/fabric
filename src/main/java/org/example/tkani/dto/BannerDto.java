package org.example.tkani.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BannerDto {

    private Long id;
    private String name;
    private String imageUrl;
    private String link;

}
