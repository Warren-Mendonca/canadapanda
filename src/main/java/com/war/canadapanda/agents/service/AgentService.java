package com.war.canadapanda.agents.service;

import com.war.canadapanda.agents.model.AgentModel;
import com.war.canadapanda.agents.payload.reponse.AgentInfo;
import com.war.canadapanda.agents.payload.request.AgentSignUp;
import com.war.canadapanda.core.payload.response.MessageResponse;
import com.war.canadapanda.core.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    public AgentInfo getAgentByPrimaryService(String primaryServid) {
        AgentModel agentModel = agentRepository.findAgentByPrimaryServiceKeyContains("+" + primaryServid)
                .orElseThrow(() -> new RuntimeException("Unable to match any Agents"));
        return new AgentInfo(agentModel.getFirstName(), agentModel.getLastName(), agentModel.getEmail(),
                agentModel.getPhonenum(), agentModel.getServices());

    }

    public MessageResponse registerAgent(AgentSignUp signUpPayload) {
        AgentModel agentModel = new AgentModel(signUpPayload.getEmail(), signUpPayload.getFirstName(),
                signUpPayload.getLastName(), signUpPayload.getServices(),
                signUpPayload.getPrimaryServiceKey(), signUpPayload.getPhoneNum());
        AgentModel result = agentRepository.save(agentModel);
        return new MessageResponse(true, "Added new agent with agentid" + result.getAgentid());
    }
}
