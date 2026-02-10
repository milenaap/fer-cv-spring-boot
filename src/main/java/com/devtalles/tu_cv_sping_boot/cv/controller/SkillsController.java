package com.devtalles.tu_cv_sping_boot.cv.controller;

import com.devtalles.tu_cv_sping_boot.cv.model.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;




@Controller
@RequestMapping("/skills")
public class SkillsController {

//    private final List<Skill> skills = new ArrayList<>();
    private final List<Skill> skills = List.of(
         new Skill("Java", "Avanzado"),
         new Skill("JS", "Medio"),
         new Skill("CSS", "Avanzado"),
         new Skill("Python", "Basico"),
         new Skill("PHP", "Medio")
        );

    @GetMapping
    public String index(@RequestParam(defaultValue = "", required = false) String filter, Model model){
        List<Skill> filteredSkills = skills.stream()
                        .filter(skill-> skill.getName().toLowerCase().contains(filter.toLowerCase()))
                        .toList();
        model.addAttribute("skills", filteredSkills);
        model.addAttribute("filter", filter);
        return "skills";
    }

    @GetMapping("/{index}")
    public String show(@PathVariable int index, Model model){
        if (index>= 0 && index < skills.size()) {
            Skill skill = skills.get(index);
            model.addAttribute("skill", skill);
            return "show-skill";
        }

        return "redirect:/skills";

    }

    @GetMapping("/{name}/{level}")
    public String showFilteredSkill(
            @PathVariable String name,
            @PathVariable String level,
            Model model
    ){

        List<Skill> filteredSkills = skills.stream()
                .filter(skill-> skill.getName().toLowerCase().equalsIgnoreCase(name)
                && skill.getLevel().equalsIgnoreCase(level)
                )
                .toList();
        model.addAttribute("skills", filteredSkills);
        model.addAttribute("filterMessage", "Filtro: " + name + " - " + level);
        return "/skills";

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
