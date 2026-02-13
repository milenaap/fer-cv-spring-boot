package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_portfolio_backend.models.PersonalInfo;

public interface IPersonalInfoService {

    List<PersonalInfo> index();

    Optional<PersonalInfo> show(Long id);

    PersonalInfo store(PersonalInfo personalInfo);

    void destroy(Long id);
}
