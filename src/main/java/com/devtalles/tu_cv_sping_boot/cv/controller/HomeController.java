package com.devtalles.tu_cv_sping_boot.cv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index", "/home"})
    public String show(Model model){
        // Agrega el tituto y el contenido
        model.addAttribute("title", "Home");
        model.addAttribute("content", "index");
        // Retorna la plantilla
        return "layouts/main";
    }
}
