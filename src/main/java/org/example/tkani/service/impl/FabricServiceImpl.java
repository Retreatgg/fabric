package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.FabricCreateAndUpdateDto;
import org.example.tkani.dto.FabricDto;
import org.example.tkani.mapper.FabricMapper;
import org.example.tkani.model.Fabric;
import org.example.tkani.repository.FabricRepository;
import org.example.tkani.service.*;
import org.example.tkani.specification.FabricSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class FabricServiceImpl implements FabricService {

    private final SubcategoryService subcategoryService;
    private final FabricRepository fabricRepository;
    private final BadgeService badgeService;
    private final FabricMapper fabricMapper;
    private final FileService fileService;

    @Override
    public FabricDto create(FabricCreateAndUpdateDto fabricCreateAndUpdateDto) {
        Fabric fabric = buildFabric(fabricCreateAndUpdateDto);
        fabricRepository.save(fabric);
        return fabricMapper.toDto(fabric);
    }

    @Override
    public Page<FabricDto> getAll(Long subcategoryId, Integer page, Integer size, String search, Long categoryId) {
        Specification<Fabric> spec = FabricSpecification
                .bySubcategoryId(subcategoryId)
                .and(FabricSpecification.bySearch(search))
                .and(FabricSpecification.isEnabled(true))
                .and(FabricSpecification.byCategoryId(categoryId));

        if(page > 0) {
            page = page - 1;
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Fabric> fabrics = fabricRepository.findAll(spec, pageable);
        return fabrics.map(fabricMapper::toDto);
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
                .badge(badgeService.findById(fabricCreateAndUpdateDto.getBadgeId()))
                .createdAt(Instant.now())
                .enabled(true)
                .image(fileService.saveUploadedFile(fabricCreateAndUpdateDto.getImage(), "/files"))
                .subcategory(subcategoryService.findById(fabricCreateAndUpdateDto.getSubcategoryId()))
                .build();
    }
}
