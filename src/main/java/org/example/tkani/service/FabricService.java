package org.example.tkani.service;


import jakarta.validation.Valid;
import org.example.tkani.dto.FabricCreateDto;
import org.example.tkani.dto.FabricDto;

import java.util.List;

public interface FabricService {
    FabricDto create(@Valid FabricCreateDto fabricCreateDto);
    List<FabricDto> getAll(Long categoryId);

    FabricDto getById(Long id);
}
