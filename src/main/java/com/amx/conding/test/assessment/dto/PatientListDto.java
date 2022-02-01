package com.amx.conding.test.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@XmlRootElement(name = "patients")
public class PatientListDto {
    //    @XmlElementWrapper
//    @XmlElement(name = "patient")
    List<PatientDto> patients;
}
