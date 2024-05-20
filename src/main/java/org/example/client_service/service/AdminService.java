package org.example.client_service.service;

import org.example.client_service.models.Agence;
import org.example.client_service.models.Agent;
import org.example.client_service.repository.AgenceRepository;
import org.example.client_service.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    public AgentRepository agentRepository;
    AgenceRepository agenceRepository;

    public Agent createAgent(Agent agent) {

        return agentRepository.save(agent);
    }

    public Agence createAgence(Agence agence) {

        return
                agenceRepository.save(agence);
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

    public void deleteAgent(Agent agent) {

        agentRepository.delete(agent);

    }

    public void deleteAgence(Agence agence) {
        agenceRepository.delete(agence);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

//    public List<Agence> getAllAgences() {
//        return agenceRepository.findAll();
//    }

}
