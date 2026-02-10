package com.devtalles.tu_cv_sping_boot.cv.controller;

import com.devtalles.tu_cv_sping_boot.cv.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cv")
public class CvController {

    @GetMapping({"/index", "", "/"})
    public String index(Model model){
        Person person = new Person("Milena", "Aguilar", "Developer");
        model.addAttribute("name", "Mi");
        model.addAttribute("person", person);
        return "index"; // Retornamos el nombre de la vista
    }

    @GetMapping("/about")
    public String about(){
        return "about"; // Retornamos el nombre de la vista
    }



}
