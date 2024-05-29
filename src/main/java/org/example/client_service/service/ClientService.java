package org.example.client_service.service;

import org.example.client_service.models.Client;
import org.example.client_service.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }



    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(Long id, Client clientDetails) {
        return clientRepository.findById(id).map(client -> {
            client.setFirstName(clientDetails.getFirstName());
            client.setLastName(clientDetails.getLastName());
            client.setEmail(clientDetails.getEmail());
            client.setAddress(clientDetails.getAddress());
            client.setCin(clientDetails.getCin());
            client.setBirthdate(clientDetails.getBirthdate());
            client.setTelephone(clientDetails.getTelephone());
            client.setPassword(clientDetails.getPassword());
            client.setCreatedDate(clientDetails.getCreatedDate());
            client.setCommercialRn(clientDetails.getCommercialRn());
            client.setPatent(clientDetails.getPatent());
            return clientRepository.save(client);
        });
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
    public Client ClientExist(String username,String password){
        return clientRepository.findByEmailAndPassword(username,password);
    }
}
