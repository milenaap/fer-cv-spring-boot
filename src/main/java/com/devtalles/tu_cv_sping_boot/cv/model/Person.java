package com.devtalles.tu_cv_sping_boot.cv.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Person  {

    @Value("${person.firstname}")
    private String firstName;
    private String lastName;
    private String profession;
}
