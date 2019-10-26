package com.war.canadapanda.core.repositories;

import com.war.canadapanda.agents.model.AgentModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface AgentRepository extends CrudRepository<AgentModel, String> {
    Optional<AgentModel> findAgentByPrimaryServiceKeyContains(String primaryServKey);
}
