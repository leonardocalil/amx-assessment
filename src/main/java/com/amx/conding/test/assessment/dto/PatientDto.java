package com.amx.conding.test.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "patient")
public class PatientDto {

    private Integer id;
    private String gender;
    private String name;
    private String state;
    private String dob;
}
