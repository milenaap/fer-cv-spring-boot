package com.portfolio.my_portfolio_backend.repositories;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.portfolio.my_portfolio_backend.models.PersonalInfo;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository {


    
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PersonalInfo> personalInfoRowMapper = (rs, numRow) -> {

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(rs.getLong("id"));
        personalInfo.setFirstName(rs.getString("first_name"));
        personalInfo.setLastName(rs.getString("last_name"));
        personalInfo.setTitle(rs.getString("title"));
        personalInfo.setProfileDescription(rs.getString("profile_description"));
        personalInfo.setProfileImageUrl(rs.getString("profile_image_url"));
        personalInfo.setYearsOfExperience(rs.getObject("years_of_experience", Integer.class)); // Usar getObject para nulos
        personalInfo.setEmail(rs.getString("email"));
        personalInfo.setPhone(rs.getString("phone"));
        personalInfo.setLinkedinUrl(rs.getString("linkedin_url"));
        personalInfo.setGithubUrl(rs.getString("github_url"));
        return personalInfo;
    };



    @Override
    public List<PersonalInfo> index() {
        String sql = "SELECT * FROM personal_info";
        return jdbcTemplate.query(sql, personalInfoRowMapper);
    }
    
    @Override
    public Optional<PersonalInfo> show(Long id) {
        String sql = "SELECT * FROM personal_info WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            
            return Optional.empty();
        }
    }

    @Override
    public PersonalInfo store(PersonalInfo personalInfo) {
        if(personalInfo.getId()==null){
            String sql = "INSERT INTO personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience, email, phone, linkedin_url, github_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, personalInfo.getFirstName());
                ps.setString(2, personalInfo.getLastName());
                ps.setString(3, personalInfo.getTitle());
                ps.setString(4, personalInfo.getProfileDescription());
                ps.setString(5, personalInfo.getProfileImageUrl());

                // ps.setInt(6, personalInfo.getYearsOfExperience());
                if(personalInfo.getYearsOfExperience() != null) {
                    ps.setInt(6, personalInfo.getYearsOfExperience());
                } else {
                    ps.setNull(6, java.sql.Types.INTEGER);
                }

                ps.setString(7, personalInfo.getEmail());
                ps.setString(8, personalInfo.getPhone());
                ps.setString(9, personalInfo.getLinkedinUrl());
                ps.setString(10, personalInfo.getGithubUrl());
                return ps;
            }, keyHolder);
            
            Number generateId = keyHolder.getKey();
            if(generateId != null) {
                personalInfo.setId(generateId.longValue());
            }
        }else {
           String slq = "UPDATE personal_info SET first_name = ?, last_name = ?, title = ?, profile_description = ?, profile_image_url = ?, years_of_experience = ?, email = ?, phone = ?, linkedin_url = ?, github_url = ? WHERE id = ?";
           jdbcTemplate.update(slq,
           personalInfo.getFirstName(),
           personalInfo.getLastName(),
           personalInfo.getTitle(),
           personalInfo.getProfileDescription(),
           personalInfo.getProfileImageUrl(),
           personalInfo.getYearsOfExperience(),
           personalInfo.getEmail(),
           personalInfo.getPhone(),
           personalInfo.getLinkedinUrl(),
           personalInfo.getGithubUrl(),
           personalInfo.getId());
        }
        return personalInfo;
    }

    @Override
    public void destroy(Long id) {
        String sql = "DELETE FROM personal_info WHERE id = ?";
        jdbcTemplate.update(sql, id);
        
    } 
    
}
