package com.portfolio.my_portfolio_backend.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.my_portfolio_backend.models.PersonalInfo;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;

@RestController
@RequestMapping("/api/v1/personal-info")
public class PersonalInfoController {

    private final IPersonalInfoService personalInfoService;

    public PersonalInfoController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping({"", "/"})
    public List<PersonalInfo> index() {
        return personalInfoService.index();
    }

    @GetMapping("/{id}")
    public PersonalInfo show(@PathVariable Long id) {
        Optional<PersonalInfo> personalInfo = personalInfoService.show(id);
        if(personalInfo.isPresent()){
            return personalInfo.get();
        }else{
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "Información personal no disponible en el ID: " + id);


        }
    } 
    
    @PostMapping("/store")
    public ResponseEntity<PersonalInfo> store(@RequestBody PersonalInfo personalInfo) {
        PersonalInfo newPersonalInfo = personalInfoService.store(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }
}


