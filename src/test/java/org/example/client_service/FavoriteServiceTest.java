package org.example.client_service;

import jakarta.transaction.Transactional;
import org.example.client_service.models.Client;
import org.example.client_service.repository.ClientRepository;
import org.example.client_service.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FavoriteServiceTest {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private ClientRepository clientRepository;
    @Test
    @Transactional
    public void testAddFavoriteCreancier() {
        favoriteService.addFavoriteCreancier( 1L,  1L);
        System.out.println("Creancier ajouté avec succès");
    }

    @Test
    @Transactional
    public void testremoveFavoriteCreancier() {
        Client client = clientRepository.findById(1L).get();
        System.out.println(client.getFavoriteCreanciers());
        favoriteService.removeFavoriteCreancier( 1L,  1L);
        Client client2 = clientRepository.findById(1L).get();
        System.out.println(client2.getFavoriteCreanciers());
        System.out.println();
        System.out.println("Creancier retiré avec succès");
    }

}
