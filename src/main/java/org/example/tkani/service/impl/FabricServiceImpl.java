package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.FabricCreateDto;
import org.example.tkani.dto.FabricDto;
import org.example.tkani.mapper.FabricMapper;
import org.example.tkani.model.Fabric;
import org.example.tkani.repository.FabricRepository;
import org.example.tkani.service.CategoryService;
import org.example.tkani.service.FabricService;
import org.example.tkani.service.FileService;
import org.example.tkani.specification.FabricSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FabricServiceImpl implements FabricService {

    private final FabricRepository fabricRepository;
    private final CategoryService categoryService;
    private final FabricMapper fabricMapper;
    private final FileService fileService;

    @Override
    public FabricDto create(FabricCreateDto fabricCreateDto) {
        Fabric fabric = buildFabric(fabricCreateDto);
        fabricRepository.save(fabric);
        return fabricMapper.toDto(fabric);
    }

    @Override
    public List<FabricDto> getAll(Long categoryId) {
        Specification<Fabric> spec = FabricSpecification.byCategoryId(categoryId);
        List<Fabric> fabrics = fabricRepository.findAll(spec);
        return fabricMapper.toListDto(fabrics);
    }

    @Override
    public FabricDto getById(Long id) {
        Fabric fabric = fabricRepository.findById(id).orElseThrow();
        return fabricMapper.toDto(fabric);
    }

    private Fabric buildFabric(FabricCreateDto fabricCreateDto) {
        return Fabric.builder()
                .name(fabricCreateDto.getName())
                .description(fabricCreateDto.getDescription())
                .price(fabricCreateDto.getPrice())
                .createdAt(Instant.now())
                .enabled(true)
                .image(fileService.saveUploadedFile(fabricCreateDto.getImage(), "/files"))
                .category(categoryService.findById(fabricCreateDto.getCategoryId()))
                .build();
    }
}
