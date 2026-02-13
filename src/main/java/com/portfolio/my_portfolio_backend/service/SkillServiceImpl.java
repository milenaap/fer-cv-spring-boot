package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.my_portfolio_backend.models.Skill;
import com.portfolio.my_portfolio_backend.repositories.ISkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements ISkillService {

    private final ISkillRepository skillRepository;

    
     @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
      
    }



    @Override
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }



     @Override
    public Skill save(Skill skill) {
        if( skill.getLevelPercentage() < 0 || skill.getLevelPercentage() > 100){
            throw new IllegalArgumentException("El % es incorrecto debe estar entre 0 y 100");
        }
       return skillRepository.save(skill);
    }

    

    @Override
    public List<Skill> findByPersonalInfoId(Long personalInfoId) {
       return skillRepository.findByPersonalInfoId(personalInfoId);
    }

   

    @Override
    public void deleteById(Long id) {
        
        skillRepository.deleteById(id);

    }


}
