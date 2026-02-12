package com.devtalles.tu_cv_sping_boot.cv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devtalles.tu_cv_sping_boot.cv.model.Person;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cv")
@RequiredArgsConstructor
public class CvController {

    private final Person person;

    @GetMapping({"/index", "", "/"})
    public String index(Model model){
        // Person person = new Person("Milena", "Aguilar", "Developer");
        // model.addAttribute("name", "Mi");
        // model.addAttribute("person", person);
        model.addAttribute("name", person.getFirstName());
        return "index"; // Retornamos el nombre de la vista
    }

    @GetMapping("/about")
    public String about(){
        return "about"; // Retornamos el nombre de la vista
    }



}
