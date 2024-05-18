package org.example.client_service.controllers;

import org.example.client_service.models.Client;
import org.example.client_service.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/agent")
public class AgentController {
    AgentService agentService;

    @PostMapping("/create-client")
    public ResponseEntity<Boolean> createClient(
            @RequestBody Client client
    ) {

        return ResponseEntity.ok(agentService.createClient(client));
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
