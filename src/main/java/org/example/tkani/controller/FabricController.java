package org.example.tkani.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.FabricCreateAndUpdateDto;
import org.example.tkani.dto.FabricDto;
import org.example.tkani.service.FabricService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<FabricDto>> getFabrics(
            @RequestParam(name = "subcategoryId", defaultValue = "0") Long subcategoryId,
            @RequestParam(name = "categoryId", defaultValue = "0") Long categoryId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "search", required = false) String search
    ) {
        return ResponseEntity.ok(fabricService.getAll(subcategoryId, page, size, search, categoryId));
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
