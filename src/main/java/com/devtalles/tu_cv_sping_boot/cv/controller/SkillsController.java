package com.devtalles.tu_cv_sping_boot.cv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devtalles.tu_cv_sping_boot.cv.model.Skill;




@Controller
@RequestMapping("/skills")
public class SkillsController {

//    private final List<Skill> skills = new ArrayList<>();
    private final List<Skill> skills = new ArrayList<>(List.of(
        new Skill("Java", "Avanzado"),
        new Skill("JS", "Medio"),
        new Skill("CSS", "Avanzado"),
        new Skill("Python", "Basico"),
        new Skill("PHP", "Medio")
    )

);

    @GetMapping
    public String index(@RequestParam(defaultValue = "", required = false) String filter, Model model){
        List<Skill> filteredSkills = skills.stream()
                        .filter(skill-> skill.getName().toLowerCase().contains(filter.toLowerCase()))
                        .toList();
        model.addAttribute("skills", filteredSkills);
        model.addAttribute("filter", filter);

        model.addAttribute("title", "Habilidades"); // Carga el título
        model.addAttribute("content", "skills/index"); // Carga la vista
        // Retorna la plantilla
        return "layouts/main";
        // return "skills";
    }

    @GetMapping("/id/{index}")
    public String show(@PathVariable int index, Model model){
        if (index>= 0 && index < skills.size()) {
            Skill skill = skills.get(index);
            model.addAttribute("skill", skill);

             // Agrega el tituto y el contenido
            model.addAttribute("title", "Show Skill");
            model.addAttribute("content", "skills/show");
            // Retorna la plantilla
            return "layouts/main";
            // return "show-skill";
        }

        return "redirect:/skills";

    }

    @GetMapping("/filter/{name}/{level}")
    public String showFilteredSkill(
            @PathVariable String name,
            @PathVariable String level,
            Model model
    ){

        List<Skill> filteredSkills = skills.stream()
                .filter(skill-> skill.getName().equalsIgnoreCase(name)
                && skill.getLevel().equalsIgnoreCase(level)
                )
                .toList();
        model.addAttribute("skills", filteredSkills);
        model.addAttribute("filterMessage", "Filtro: " + name + " - " + level);
      
        model.addAttribute("title", "Habildades");
        model.addAttribute("content", "skills/index");
        // Retorna la plantilla
        return "layouts/main";

    }


    // @GetMapping("/name/{name}")
    // public String showFilteredSkill(
    //         @PathVariable String name,
    //         Model model
    // ){
    //     List<Skill> filteredSkills = skills.stream()
    //             .filter(skill-> skill.getName().equalsIgnoreCase(name))
    //             .toList();

    //     if(filteredSkills.isEmpty()){
    //         model.addAttribute("filterMessage", "No se encontraron registros para: " + name);
    //         return "forward:/skills";
    //     }

    //     model.addAttribute("skills", filteredSkills);
    //     model.addAttribute("filterMessage", "Filtro: " + name);

    //     model.addAttribute("title", "Habilidades");
    //     model.addAttribute("content", "skills/index");
    //     // Retorna la plantilla
    //     return "layouts/main";
    // }

      @GetMapping("/name/{name}")
    public String showFilteredSkill(
            @PathVariable String name,
            RedirectAttributes redirectAttributes
    ){
        List<Skill> filteredSkills = skills.stream()
                .filter(skill-> skill.getName().equalsIgnoreCase(name))
                .toList();

        if(filteredSkills.isEmpty()){
            redirectAttributes.addFlashAttribute("filterMessage", "No se encontraron registros para: " + name);
            return "redirect:/skills?filter=" + name;
        }

        redirectAttributes.addFlashAttribute("skills", filteredSkills);
        redirectAttributes.addFlashAttribute("filterMessage", "Filtro: " + name);
        
        return "redirect:/skills?filter=" + name;
    }


    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("skill", new Skill());
         // Agrega el tituto y el contenido
            model.addAttribute("title", "Create Skill");
            model.addAttribute("content", "skills/create");
            // Retorna la plantilla
            return "layouts/main";
        // return "create-skill";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute Skill skill){
        skills.add(skill);
        return "redirect:/skills";
    }

    
}
