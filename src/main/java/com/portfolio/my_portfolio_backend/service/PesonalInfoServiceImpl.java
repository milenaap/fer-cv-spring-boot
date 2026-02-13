package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.my_portfolio_backend.models.PersonalInfo;
import com.portfolio.my_portfolio_backend.repositories.IPersonalInfoRepository;

@Service
public class PesonalInfoServiceImpl implements IPersonalInfoService {

    private final IPersonalInfoRepository personalInfoRepository;
    

    public PesonalInfoServiceImpl(IPersonalInfoRepository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }

    @Override
    public void destroy(Long id) {
        personalInfoRepository.destroy(id);   
    }

    @Override
    public List<PersonalInfo> index() {
        return personalInfoRepository.index();
    }

    @Override
    public Optional<PersonalInfo> show(Long id) {
         return personalInfoRepository.show(id);
    }

    @Override
    public PersonalInfo store(PersonalInfo personalInfo) {
        return personalInfoRepository.store(personalInfo);
    }

}
