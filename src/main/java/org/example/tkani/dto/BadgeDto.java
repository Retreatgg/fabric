package org.example.tkani.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeDto {

    private Long id;
    private String name;
    private String textColor;
    private String backgroundColor;

}
