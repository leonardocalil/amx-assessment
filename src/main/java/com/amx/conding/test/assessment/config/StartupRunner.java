package com.amx.conding.test.assessment.config;

import com.amx.conding.test.assessment.model.FieldMapping;
import com.amx.conding.test.assessment.model.FieldValueMapping;
import com.amx.conding.test.assessment.repository.FieldMappingRepository;
import com.amx.conding.test.assessment.util.EnumTypes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {

    private final FieldMappingRepository fieldMappingRepository;

    public StartupRunner(FieldMappingRepository fieldMappingRepository) {
        this.fieldMappingRepository = fieldMappingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        FieldMapping fieldMapping = FieldMapping.builder()
                .xmlName("id")
                .jsonName("patientid").build();
        fieldMappingRepository.save(fieldMapping);

        fieldMapping = FieldMapping.builder()
                .xmlName("gender")
                .jsonName("sex").build();

        List<FieldValueMapping> values = new ArrayList<>();
        values.add(FieldValueMapping.builder()
                .fieldMapping(fieldMapping)
                .xmlType(EnumTypes.STRING)
                .xmlValue("m")
                .jsonType(EnumTypes.STRING)
                .jsonValue("male")
                .build());
        values.add(FieldValueMapping.builder()
                .fieldMapping(fieldMapping)
                .xmlType(EnumTypes.STRING)
                .xmlValue("f")
                .jsonType(EnumTypes.STRING)
                .jsonValue("female")
                .build());
        fieldMapping.setFieldValuesMapping(values);
        fieldMappingRepository.save(fieldMapping);


        fieldMapping = FieldMapping.builder()
                .xmlName("state")
                .jsonName("state").build();

        values = new ArrayList<>();
        values.add(FieldValueMapping.builder()
                .fieldMapping(fieldMapping)
                .xmlType(EnumTypes.STRING)
                .xmlValue("Michigan")
                .jsonType(EnumTypes.STRING)
                .jsonValue("MI")
                .build());
        values.add(FieldValueMapping.builder()
                .fieldMapping(fieldMapping)
                .xmlType(EnumTypes.STRING)
                .xmlValue("Ohio")
                .jsonType(EnumTypes.STRING)
                .jsonValue("OH")
                .build());
        fieldMapping.setFieldValuesMapping(values);
        fieldMappingRepository.save(fieldMapping);

        fieldMapping = FieldMapping.builder()
                .xmlName("dob")
                .jsonName("age").build();

        values = new ArrayList<>();
        values.add(FieldValueMapping.builder()
                .fieldMapping(fieldMapping)
                .xmlType(EnumTypes.DATE)
                .jsonType(EnumTypes.INTEGER)
                .build());

        fieldMapping.setFieldValuesMapping(values);
        fieldMappingRepository.save(fieldMapping);


    }
}
