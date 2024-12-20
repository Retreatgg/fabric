package org.example.tkani.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.FabricCreateDto;
import org.example.tkani.dto.FabricDto;
import org.example.tkani.service.FabricService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fabrics")
@RequiredArgsConstructor
public class FabricController {

    private final FabricService fabricService;

    @PostMapping()
    public ResponseEntity<FabricDto> createFabric(
            @ModelAttribute @Valid FabricCreateDto fabricCreateDto
    ) {
        return ResponseEntity.ok(fabricService.create(fabricCreateDto));
    }

    @GetMapping()
    public ResponseEntity<List<FabricDto>> getFabrics(
            @RequestParam(name = "categoryId", defaultValue = "0") Long categoryId
    ) {
        return ResponseEntity.ok(fabricService.getAll(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricDto> getFabric(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(fabricService.getById(id));
    }
}
