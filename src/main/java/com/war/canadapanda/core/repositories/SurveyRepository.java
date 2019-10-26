package com.war.canadapanda.core.repositories;

import com.war.canadapanda.survey.model.SurveyModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SurveyRepository extends CrudRepository<SurveyModel, String> {
}
