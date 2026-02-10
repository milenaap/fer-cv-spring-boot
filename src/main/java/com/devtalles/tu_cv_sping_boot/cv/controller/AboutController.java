package com.devtalles.tu_cv_sping_boot.cv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String show(Model model){
        model.addAttribute("title", "About");
        model.addAttribute("content", "about");
        return "layouts/main";
    }

}
