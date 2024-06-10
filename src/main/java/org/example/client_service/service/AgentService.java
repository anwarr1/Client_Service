package org.example.client_service.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.client_service.config.GeneratePassword;
import org.example.client_service.models.Agent;
import org.example.client_service.models.Client;
import org.example.client_service.repository.AgentRepository;
import org.example.client_service.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class AgentService {

    public ClientRepository clientRepository;
    public AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;
    SmsSender smsSender;

    public AgentService(ClientRepository clientRepository, AgentRepository agentRepository, SmsSender smsSender,PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.agentRepository = agentRepository;
        this.passwordEncoder= passwordEncoder;
        this.smsSender = smsSender;
    }

    // Fonction to register client
    public String createClient(Client client) throws IOException {
        String pwd= GeneratePassword.generateTemporaryPassword();
        client.setPassword(passwordEncoder.encode(pwd));
        smsSender.sendSms(client.getTelephone(), "your temp password is : " + client.getPassword());
        System.out.println(pwd);
        clientRepository.save(client);
        return pwd;
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

    public boolean agentExists(String email, String password) {
        Agent agent = agentRepository.findByEmailAndPassword(email, password);
        System.out.println("agent trouver est :" + agent);
        if (agent != null) {
            return true;
        }
        return false;
    }

}
