package org.example.client_service.controllers;

import org.example.client_service.models.Client;
import org.example.client_service.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/agent")
public class AgentController {
    AgentService agentService;

    @PostMapping("/create-client")

    public ResponseEntity<Boolean> createClient(
            @RequestPart("client") Client client,
            @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            Boolean result = agentService.createClientAndPieceJoint(client, imageFile);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list-clients")
    public ResponseEntity<List<Client>> getClients(

    ) {

        return ResponseEntity.ok(agentService.getAllClients());
    }

    @DeleteMapping("/delete-client")
    public ResponseEntity<Boolean> deleteAgent(
            @RequestBody Client client
    ) {

        return ResponseEntity.ok(agentService.deleteClient(client));
    }

}
