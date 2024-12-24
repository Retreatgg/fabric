package org.example.tkani.mapper;

import org.example.tkani.dto.FabricDto;
import org.example.tkani.model.Fabric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BadgeMapper.class})
public interface FabricMapper {

    @Mapping(target = "badge", source = "badge")
    @Mapping(target = "imageUrl", source = "image")
    FabricDto toDto(Fabric fabric);
    List<FabricDto> toListDto(List<Fabric> fabricList);

}
