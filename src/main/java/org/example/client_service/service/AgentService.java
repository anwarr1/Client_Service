package org.example.client_service.service;

import org.example.client_service.models.Client;
import org.example.client_service.models.PieceJointe;
import org.example.client_service.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AgentService {
    public ClientRepository clientRepository;

    public Boolean createClientAndPieceJoint(Client client, MultipartFile image) throws IOException {

        if (image != null) {
            PieceJointe pieceJointe = new PieceJointe();
            pieceJointe.setData(image.getBytes());
            pieceJointe.setUser(client);
            client.getPieces_jointes().add(pieceJointe);

        }
        clientRepository.save(client);
        return true;
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

}
