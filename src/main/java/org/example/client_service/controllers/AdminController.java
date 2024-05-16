package org.example.client_service.controllers;

import org.example.client_service.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    AdminService adminService;

    @PostMapping("/create-agent")
    public ResponseEntity<Boolean> createAgent(
//            @RequestBody Agent agent
    ) {

return ResponseEntity.ok(adminService.createAgent());
    }
    @DeleteMapping("/create-agent")
    public ResponseEntity<Boolean> deleteAgent(
//            @RequestBody Agent agent
    ) {

return ResponseEntity.ok(adminService.deleteAgent());
    }

}
