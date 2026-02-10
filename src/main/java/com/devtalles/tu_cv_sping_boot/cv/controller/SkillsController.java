package com.devtalles.tu_cv_sping_boot.cv.controller;

import com.devtalles.tu_cv_sping_boot.cv.model.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * index -> listar
 * show -> ver uno
 * create -> carga view para crear
 * store -> guardar
 * edit -> carga view para editar
 * update -> actualizar
 * destroy -> eliminar
 */

@Controller
@RequestMapping("/skills")
public class SkillsController {

    private final List<Skill> skills = new ArrayList<>();

    @GetMapping
    public String index(Model model){
        List<String> skills = List.of("Java", "Spring Boot", "Thymeleaf", "HTML", "CSS");
        List<String> list = new ArrayList<>();
        model.addAttribute("skills", skills);
        return "skills";
    }
}
