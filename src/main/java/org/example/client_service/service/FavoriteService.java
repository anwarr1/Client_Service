package org.example.client_service.service;

import jakarta.transaction.Transactional;
import org.example.client_service.models.Client;
import org.example.client_service.models.Creancier;
import org.example.client_service.repository.ClientRepository;
import org.example.client_service.repository.CreancierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CreancierRepository creancierRepository;


    //Ajouter Un creancier a la liste des creanciers favoris
    //@Transactional
    public void addFavoriteCreancier(Long clientId, Long creancierId) {

        Optional<Client> clientSended = clientRepository.findById(clientId);
        System.out.println(clientSended);
        Optional<Creancier> creancierSedend = creancierRepository.findById(creancierId);
        System.out.println(creancierSedend);

        if (clientSended.isPresent() && creancierSedend.isPresent()) {
            Client client = clientSended.get();
            Creancier creancier = creancierSedend.get();

            client.getFavoriteCreanciers().add(creancier);
            creancier.setClient(client);
            clientRepository.save(client);
            creancierRepository.save(creancier);

        }

    }

    //Supprimer Un creancier a la liste des creanciers favoris
    @Transactional
    public void removeFavoriteCreancier(Long clientId, Long creancierId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Optional<Creancier> creancierOptional = creancierRepository.findById(creancierId);

        if (clientOptional.isPresent() && creancierOptional.isPresent()) {
            Client client = clientOptional.get();
            Creancier creancier = creancierOptional.get();

            // Retirer la creancier de la liste des favoris du client
            client.getFavoriteCreanciers().remove(creancier);
            creancier.setClient(null);

            // Enregistrer le client pour mettre à jour la relation
            clientRepository.save(client);
        }
    }


}
