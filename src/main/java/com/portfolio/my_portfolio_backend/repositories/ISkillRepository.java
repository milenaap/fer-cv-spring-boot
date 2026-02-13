package com.portfolio.my_portfolio_backend.repositories;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_portfolio_backend.models.Skill;

public interface ISkillRepository {

    Skill save(Skill skill);
    Optional<Skill> findById(Long id);
    List<Skill> findAll();
    void deleteById(Long id);
    List<Skill> findByPersonalInfoId(Long personalInfoId);
}
