package org.example.tkani.controller;

import org.example.tkani.dto.BannerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @GetMapping()
    public ResponseEntity<List<BannerDto>> getBanners() {
        List<BannerDto> bannerDtoList = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            bannerDtoList.add(BannerDto.builder()
                    .id((long) i)
                    .name("banner " + i)
                    .imageUrl("banner"+ i + ".jpg")
                    .link("https://www.google.com")
                    .build());
        }

        return ResponseEntity.ok(bannerDtoList);
    }
}
