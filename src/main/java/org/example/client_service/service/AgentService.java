package org.example.client_service.service;

import org.example.client_service.models.Client;
import org.example.client_service.models.PieceJointe;
import org.example.client_service.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {
    public ClientRepository clientRepository;

    public Boolean createClientAndPieceJoint(Client client, PieceJointe pieceJointe) {

        client.getPieces_jointes().add(pieceJointe);
        pieceJointe.setUserID(client.getId());
        clientRepository.save(client);
        return true;
    }

    public String generateUniqueId() {
        return "";
    }

    public String generateAgentPassword() {
        return "";
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Boolean deleteClient(Client client) {
        return true;
    }

}
