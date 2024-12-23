package org.example.tkani.service;


import jakarta.validation.Valid;
import org.example.tkani.dto.FabricCreateAndUpdateDto;
import org.example.tkani.dto.FabricDto;

import java.util.List;

public interface FabricService {
    FabricDto create(@Valid FabricCreateAndUpdateDto fabricCreateAndUpdateDto);
    List<FabricDto> getAll(Long subcategoryId);
    FabricDto getById(Long id);
    void deleteById(Long id);
    FabricDto update(Long id, @Valid FabricCreateAndUpdateDto fabricCreateAndUpdateDto);
}
