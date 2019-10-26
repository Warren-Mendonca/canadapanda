package com.war.canadapanda.agents.controller;

import com.war.canadapanda.agents.payload.reponse.AgentInfo;
import com.war.canadapanda.agents.payload.request.AgentSignUp;
import com.war.canadapanda.agents.service.AgentService;
import com.war.canadapanda.core.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agent")
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping("/{primaryservid}")
    public AgentInfo getAgentByPrimaryService(@PathVariable(value = "primaryservid") String primaryServid) {
        return agentService.getAgentByPrimaryService(primaryServid);
    }

    @PostMapping("/addAgent")
    public MessageResponse registerAgent(@RequestBody AgentSignUp agentSignUp) {
        return agentService.registerAgent(agentSignUp);
    }
}
