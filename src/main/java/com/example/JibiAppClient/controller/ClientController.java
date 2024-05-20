package com.example.JibiAppClient.controller;

import com.example.JibiAppClient.model.Client;
import com.example.JibiAppClient.service.ClientService;
import com.example.JibiAppClient.service.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService ;

    @PostMapping("/client")
    public ResponseEntity<Client> Clientespace(@RequestBody Client client) {
        try {
            Client registeredClient = clientService.registerClient(client);
            return ResponseEntity.ok(registeredClient);
        } catch (IOException e) {

            return ResponseEntity.status(500).body(null);
        }
    }

    // Méthode pour gérer l'upload de fichiers CIN {pas encore Tester }
   /* @PostMapping("/client/upload-cin")
    public ResponseEntity<String> uploadCIN(@RequestParam("cinFront") MultipartFile cinFront,
                                            @RequestParam("cinBack") MultipartFile cinBack,
                                            @RequestParam("clientId") Long clientId) {
        try {
            clientService.uploadCINFiles(cinFront, cinBack, clientId);
            return ResponseEntity.ok("Fichiers CIN téléchargés avec succès.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Une erreur s'est produite lors du téléchargement des fichiers CIN.");
        }
    }*/
}
