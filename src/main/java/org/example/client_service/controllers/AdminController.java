package org.example.client_service.controllers;

import org.example.client_service.models.Agence;
import org.example.client_service.models.Agent;
import org.example.client_service.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    AdminService adminService;

    @PostMapping("/create-agent")
    public ResponseEntity<Agent> createAgent(
            @RequestBody Agent agent
    ) {

        return ResponseEntity.ok(adminService.createAgent(agent));
    }

    @PostMapping("/create-agence")
    public ResponseEntity<Agence> createAgence(
            @RequestBody Agence agence
    ) {

        return ResponseEntity.ok(adminService.createAgence(agence));
    }

    @DeleteMapping("/delete-agent")
    public void deleteAgent(
            @RequestBody Agent agent
    ) {

        adminService.deleteAgent(agent);
    }

    @DeleteMapping("/delete-agence")
    public void deleteAgence(
            @RequestBody Agence agence
    ) {

        adminService.deleteAgence(agence);
    }

}
