//package org.example.client_service.controllers;
//
//import org.example.client_service.models.Agence;
//import org.example.client_service.models.Agent;
//import org.example.client_service.models.Client;
//import org.example.client_service.service.AdminService;
//import org.example.client_service.service.AgenceService;
//import org.example.client_service.service.AgentService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/agence")
//public class AgenceController {
//    AgenceService agenceService;
//    AdminService adminService;
//
////    @GetMapping("/list-agences")
////    public ResponseEntity<List<Agence>> getAllAgences(
////
////    ) {
////
////        return ResponseEntity.ok(adminService.getAllAgences());
////    }
//
//    @GetMapping("/list-agents")
//    public ResponseEntity<List<Agent>> getAllAgents(
//
//    ) {
//
//        return ResponseEntity.ok(adminService.getAllAgents());
//    }
//
//    @GetMapping("/list-clients")
//    public ResponseEntity<List<Client>> getClients(
//
//    ) {
//
//        return ResponseEntity.ok(agenceService.getAllClients());
//    }
//
//
//
//}
