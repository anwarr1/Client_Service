package com.example.JibiAppClient.service;

import com.example.JibiAppClient.model.Agent;
import com.example.JibiAppClient.repository.AgentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class AgentService  {

    private AgentRepository agentRepository;

    @Autowired
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    //Verify Existance Of the Agent Information In the DB
    public boolean agentExists(String username, String password) {
        Agent agent = agentRepository.findByUsernameAndPassword(username, password);
        System.out.println("agent trouver est :"+ agent );
        if (agent!=null){
            return true;
        }
        return false;
    }

    public Agent findByusername(String username){
        return agentRepository.findByUsername(username);
    }

    public List<Agent> getAllAgents(){
        List<Agent> agents = new ArrayList<>();
        agents = agentRepository.findAll();
        return agents;
    }

    public void deleteAgent(long id){
        agentRepository.deleteById(id);
    }
    public Agent findAgentById(long id){
        Agent agent  = agentRepository.findById(id);
        return agent;
    }
}
