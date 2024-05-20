package org.example.client_service.service;

import org.example.client_service.models.Client;
import org.example.client_service.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenceService {
    ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

}
