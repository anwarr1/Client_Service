package org.example.client_service.controllers;

import org.example.client_service.models.Client;
import org.example.client_service.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/agent")
public class AgentController {
    AgentService agentService;

    @PostMapping("/create-client")

    public ResponseEntity<Client> createClient(
            @RequestPart("client") Client client) {
        try {
            Client result = agentService.createClient(client);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete-client")
    public ResponseEntity<Boolean> deleteClient(
            @RequestBody Client client
    ) {

        return ResponseEntity.ok(agentService.deleteClient(client));
    }

    @GetMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public Boolean AgentInfosVerification(@RequestParam String username, @RequestParam String password) {
        Boolean AgentExist = agentService.agentExists(username, password);
        System.out.println(AgentExist);
        return AgentExist;
    }

}
