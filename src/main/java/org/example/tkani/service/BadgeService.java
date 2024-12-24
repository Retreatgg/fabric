package org.example.tkani.service;

import org.example.tkani.dto.BadgeCreateDto;
import org.example.tkani.dto.BadgeDto;
import org.example.tkani.model.Badge;

import java.util.List;

public interface BadgeService {

    Badge findById(Long badgeId);
    List<BadgeDto> badges();
    BadgeDto create(BadgeCreateDto badgeCreateDto);
}
