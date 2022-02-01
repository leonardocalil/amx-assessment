package com.amx.conding.test.assessment.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String xmlName;

    private String jsonName;


    @OneToMany(mappedBy = "fieldMapping", cascade = CascadeType.ALL)
    private List<FieldValueMapping> fieldValuesMapping;
}
