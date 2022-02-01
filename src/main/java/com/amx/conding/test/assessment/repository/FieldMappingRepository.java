package com.amx.conding.test.assessment.repository;

import com.amx.conding.test.assessment.model.FieldMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldMappingRepository extends CrudRepository<FieldMapping, Integer> {

}
