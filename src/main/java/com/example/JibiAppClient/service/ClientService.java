package com.example.JibiAppClient.service;

import com.example.JibiAppClient.model.Client;
import com.example.JibiAppClient.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.IOException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
     private SmsSender smsSender ;

    // Fonction to register client
    public Client registerClient(Client client) throws IOException {
        client.setPassword(generateTemporaryPassword());
        smsSender.sendSms(client.getPhoneNumber(), "your temp password is : " + client.getPassword());
        return clientRepository.save(client);
    }

    //generation of a temporary password
    private String generateTemporaryPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }


    // Methode to load CIN of the client { pas encore Tester }

    /*public void uploadCINFiles(MultipartFile cinFront, MultipartFile cinBack, Long clientId) throws IOException {
        // Récupérer le client de la base de données par son ID
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client introuvable"));

        // Convertir les fichiers en tableaux de bytes
        byte[] cinFrontBytes = cinFront.getBytes();
        byte[] cinBackBytes = cinBack.getBytes();

        // Définir les fichiers CIN pour le client
        client.setCinFront(cinFrontBytes);
        client.setCinBack(cinBackBytes);

        // Mettre à jour le client dans la base de données
        clientRepository.save(client);
    }*/

}
