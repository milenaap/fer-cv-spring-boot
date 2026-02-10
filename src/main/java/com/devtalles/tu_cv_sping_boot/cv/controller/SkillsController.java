package com.devtalles.tu_cv_sping_boot.cv.controller;

import com.devtalles.tu_cv_sping_boot.cv.model.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;




@Controller
@RequestMapping("/skills")
public class SkillsController {

    private final List<Skill> skills = new ArrayList<>();

    @GetMapping
    public String index(Model model){
        model.addAttribute("skills", skills);
        return "skills";
    }



    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("skill", new Skill());
        return "create-skill";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute Skill skill){
        skills.add(skill);
        return "redirect:/skills";
    }
}
