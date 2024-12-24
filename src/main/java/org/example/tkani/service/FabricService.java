package org.example.tkani.service;


import jakarta.validation.Valid;
import org.example.tkani.dto.FabricCreateAndUpdateDto;
import org.example.tkani.dto.FabricDto;
import org.springframework.data.domain.Page;

public interface FabricService {
    FabricDto create(@Valid FabricCreateAndUpdateDto fabricCreateAndUpdateDto);
    Page<FabricDto> getAll(Long subcategoryId, Integer page, Integer size, String search, Long categoryId);
    FabricDto getById(Long id);
    void deleteById(Long id);
    FabricDto update(Long id, @Valid FabricCreateAndUpdateDto fabricCreateAndUpdateDto);
}
