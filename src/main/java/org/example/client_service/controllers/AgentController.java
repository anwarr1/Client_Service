package org.example.client_service.controllers;

import org.example.client_service.models.Agent;
import org.example.client_service.models.Client;
import org.example.client_service.service.AdminService;
import org.example.client_service.service.AgentService;
import org.example.client_service.service.ClientService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/agent")
public class AgentController {
    AgentService agentService;
    AdminService adminService;
    ClientService clientService;

     RestTemplate restTemplate;

    public AgentController(AgentService agentService, AdminService adminService, ClientService clientService,RestTemplate restTemplate) {
        this.agentService = agentService;
        this.adminService = adminService;
        this.clientService = clientService;
        this.restTemplate = restTemplate;
    }

    //Tested
    @PostMapping("/create-client")

    public ResponseEntity<Client> createClient(
            @RequestBody Client client) {
        try {
            Client result = agentService.createClient(client);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
// ouverture compte payement
    @PostMapping("/sendPostRequest")
    public String sendPostRequest(@RequestBody Client client) {

        String url = "http://localhost:8080/springrest/clients";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Client> request = new HttpEntity<>(client, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();

    }
    @DeleteMapping("/delete-client")
    public ResponseEntity<Boolean> deleteClient(
            @RequestBody Client client
    ) {

        return ResponseEntity.ok(agentService.deleteClient(client));
    }

    @GetMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public Boolean AgentInfosVerification(@RequestParam String email, @RequestParam String password) {
        Boolean AgentExist = agentService.agentExists(email, password);
        System.out.println(AgentExist);
        return AgentExist;
    }

    @GetMapping("/list-agents")
    public ResponseEntity<List<Agent>> getAllAgents(

    ) {

        return ResponseEntity.ok(adminService.getAllAgents());
    }

    @GetMapping("/list-clients")
    public ResponseEntity<List<Client>> getClients(

    ) {

        return ResponseEntity.ok(clientService.getAllClients());
    }

}
