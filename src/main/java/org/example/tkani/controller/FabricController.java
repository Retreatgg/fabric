package org.example.tkani.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("mvcFabricController")
@RequiredArgsConstructor
@RequestMapping("/fabrics")
public class FabricController {

    @GetMapping("")
    public String getFabrics() {
        return "fabrics";
    }
}
