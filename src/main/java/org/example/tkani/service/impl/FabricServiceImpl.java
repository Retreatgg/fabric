package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.FabricCreateAndUpdateDto;
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
    public FabricDto create(FabricCreateAndUpdateDto fabricCreateAndUpdateDto) {
        Fabric fabric = buildFabric(fabricCreateAndUpdateDto);
        fabricRepository.save(fabric);
        return fabricMapper.toDto(fabric);
    }

    @Override
    public List<FabricDto> getAll(Long categoryId) {
        Specification<Fabric> spec = FabricSpecification
                .byCategoryId(categoryId)
                .and(FabricSpecification.isEnabled(true));

        List<Fabric> fabrics = fabricRepository.findAll(spec);
        return fabricMapper.toListDto(fabrics);
    }

    @Override
    public FabricDto getById(Long id) {
        Fabric fabric = fabricRepository.findById(id).orElseThrow();
        return fabricMapper.toDto(fabric);
    }

    @Override
    public void deleteById(Long id) {
        Fabric fabric = fabricRepository.findById(id).orElseThrow();
        fabric.setEnabled(false);
        fabricRepository.save(fabric);
    }

    @Override
    public FabricDto update(Long id, FabricCreateAndUpdateDto fabricCreateAndUpdateDto) {
        Fabric fabric = buildFabric(fabricCreateAndUpdateDto);
        fabric.setId(id);
        fabricRepository.save(fabric);
        return fabricMapper.toDto(fabric);
    }

    private Fabric buildFabric(FabricCreateAndUpdateDto fabricCreateAndUpdateDto) {
        return Fabric.builder()
                .name(fabricCreateAndUpdateDto.getName())
                .description(fabricCreateAndUpdateDto.getDescription())
                .price(fabricCreateAndUpdateDto.getPrice())
                .createdAt(Instant.now())
                .enabled(true)
                .image(fileService.saveUploadedFile(fabricCreateAndUpdateDto.getImage(), "/files"))
                .category(categoryService.findById(fabricCreateAndUpdateDto.getCategoryId()))
                .build();
    }
}
