package org.example.client_service.controllers;

import org.example.client_service.models.BankAccount;
import org.example.client_service.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/favorite")
@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;
    @PostMapping("/add/{clientId}/{criancierId}")
    public void AddFavoriteCreancier(@PathVariable Long clientId , @PathVariable Long criancierId) {
        System.out.println("Entrer a la fonction "+clientId+criancierId);
        favoriteService.addFavoriteCreancier(clientId,criancierId);
    }
    @PostMapping("/remove/{clientId}/{criancierId}")
    public void removeFavoriteCreancier(@PathVariable Long clientId, @PathVariable Long criancierId) {
        favoriteService.removeFavoriteCreancier(clientId,criancierId);
    }

}
