package org.example.tkani.mapper;

import org.example.tkani.dto.BadgeDto;
import org.example.tkani.model.Badge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BadgeMapper {

    @Mapping(target = "textColor", source = "textColor")
    @Mapping(target = "backgroundColor", source = "backgroundColor")
    BadgeDto toDto(Badge badge);
    List<BadgeDto> toListDto(List<Badge> badges);

}
