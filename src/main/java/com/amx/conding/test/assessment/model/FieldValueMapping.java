package com.amx.conding.test.assessment.model;

import com.amx.conding.test.assessment.util.EnumTypes;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldValueMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="field_mapping_id")
    private FieldMapping fieldMapping;

    @Enumerated(EnumType.STRING)
    private EnumTypes xmlType;
    private String xmlValue;
    @Enumerated(EnumType.STRING)
    private EnumTypes jsonType;
    private String jsonValue;
}
