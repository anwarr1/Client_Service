package org.example.client_service.service;

import org.example.client_service.models.Agent;
import org.example.client_service.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    public AgentRepository agentRepository;

    public Boolean createAgent(Agent agent) {
        return true;
    }

    ;

    public String generateUniqueId() {
        return "";
    }

    ;

    public String generateAgentPassword() {
        return "";
    }

    ;

    public Boolean deleteAgent(Agent agent) {
        return true;
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }



}
