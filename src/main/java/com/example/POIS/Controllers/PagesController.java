package com.example.POIS.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequiredArgsConstructor
public class PagesController {
    @GetMapping("/")
    public String get(Model model) {
        return "main";
    }
    @GetMapping("/main")
    public String getMainPage(Model model) {
        return "main";
    }
    @GetMapping("/pages/laba1")
    public String getPageLaba1(Model model) {
        return "pages/laba1";
    }
    @GetMapping("pages/laba2")
    public String getPageLaba2(Model model) {
        return "pages/laba2";
    }
    @GetMapping("pages/laba3")
    public String getPageLaba3(Model model) {
        return "pages/laba3";
    }
    @GetMapping("pages/laba4")
    public String getPageLaba4(Model model) {
        return "pages/laba4";
    }
}
