package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.BadgeCreateDto;
import org.example.tkani.dto.BadgeDto;
import org.example.tkani.mapper.BadgeMapper;
import org.example.tkani.model.Badge;
import org.example.tkani.repository.BadgeRepository;
import org.example.tkani.service.BadgeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;
    private final BadgeMapper badgeMapper;

    @Override
    public Badge findById(Long badgeId) {
        return badgeRepository.findById(badgeId).orElseThrow();
    }

    @Override
    public List<BadgeDto> badges() {
        return badgeMapper.toListDto(badgeRepository.findAll());
    }

    @Override
    public BadgeDto create(BadgeCreateDto badgeCreateDto) {
        Badge badge = buildBadge(badgeCreateDto);
        return badgeMapper.toDto(badgeRepository.save(badge));
    }


    private Badge buildBadge(BadgeCreateDto badgeCreateDto) {
        String textColor = badgeCreateDto.getTextColor();
        String backgroundColor = badgeCreateDto.getBackgroundColor();

        if(textColor == null) {
           textColor = "#e6e6e6";
        }

        if(backgroundColor == null) {
            backgroundColor = "#141414";
        }

        return Badge.builder()
                .name(badgeCreateDto.getName())
                .backgroundColor(backgroundColor)
                .textColor(textColor)
                .build();
    }
}
