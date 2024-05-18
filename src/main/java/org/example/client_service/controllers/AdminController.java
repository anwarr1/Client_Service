package org.example.client_service.controllers;

import org.example.client_service.models.Agent;
import org.example.client_service.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    AdminService adminService;

    @PostMapping("/create-agent")
    public ResponseEntity<Boolean> createAgent(
            @RequestBody Agent agent
    ) {

        return ResponseEntity.ok(adminService.createAgent(agent));
    }

    @GetMapping("/list-agents")
    public ResponseEntity<List<Agent>> getAgents(

    ) {

        return ResponseEntity.ok(adminService.getAllAgents());
    }

    @DeleteMapping("/delete-agent")
    public ResponseEntity<Boolean> deleteAgent(
            @RequestBody Agent agent
    ) {

        return ResponseEntity.ok(adminService.deleteAgent(agent));
    }

}
