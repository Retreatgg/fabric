package org.example.tkani.controller;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.BadgeCreateDto;
import org.example.tkani.dto.BadgeDto;
import org.example.tkani.service.BadgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/badges")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;

    @PostMapping()
    public ResponseEntity<BadgeDto> createBadge(
            @RequestBody BadgeCreateDto badgeCreateDto
    ) {
        return ResponseEntity.ok(badgeService.create(badgeCreateDto));
    }
}
