package org.example.tkani.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.FabricCreateAndUpdateDto;
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
            @ModelAttribute @Valid FabricCreateAndUpdateDto fabricCreateAndUpdateDto
    ) {
        return ResponseEntity.ok(fabricService.create(fabricCreateAndUpdateDto));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFabric(@PathVariable(name = "id") Long id) {
        fabricService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FabricDto> updateFabric(
            @PathVariable(name = "id") Long id,
            @ModelAttribute @Valid FabricCreateAndUpdateDto fabricCreateAndUpdateDto
    ) {
        return ResponseEntity.ok(fabricService.update(id, fabricCreateAndUpdateDto));
    }
}
