package org.example.client_service.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.client_service.models.Agent;
import org.example.client_service.models.Client;
import org.example.client_service.repository.AgentRepository;
import org.example.client_service.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AgentService {

    public ClientRepository clientRepository;
    public AgentRepository agentRepository;
    SmsSender smsSender;

    // Fonction to register client
    public Client createClient(Client client) throws IOException {
        client.setPassword(generateTemporaryPassword());
        smsSender.sendSms(client.getTelephone(), "your temp password is : " + client.getPassword());
        return clientRepository.save(client);
    }

    private String generateTemporaryPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public String generateUniqueId() {
        return "";
    }

    public String generateAgentPassword() {
        return "";
    }

    public Boolean deleteClient(Client client) {
        return true;
    }

    public boolean agentExists(String username, String password) {
        Agent agent = agentRepository.findByUsernameAndPassword(username, password);
        System.out.println("agent trouver est :" + agent);
        if (agent != null) {
            return true;
        }
        return false;
    }

}
