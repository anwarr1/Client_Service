package org.example.client_service.controllers;

import lombok.AllArgsConstructor;
import org.example.client_service.models.Transaction;
import org.example.client_service.models.servicesAgence.Facture;
import org.example.client_service.repository.FactureRepository;
import org.example.client_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/paiement")
@AllArgsConstructor
public class PaiementController {


    private PaymentService paymentService;

    private FactureRepository factureRepository;

    @GetMapping("/facture/{id}")
    public ResponseEntity<Facture> getFactureByIdAndCustomerId(
            @PathVariable Long id,
            @RequestParam Long customerId) {
        Optional<Facture> facture = paymentService.findFactureByIdAndCustomerId(id, customerId);
        return facture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/confirmer")
    public ResponseEntity<?> confirmerPaiement(@RequestParam Long factureId, @RequestParam String phoneNumber) {
        try {
            Transaction transaction = paymentService.confirmerPaiementAcc(factureId, phoneNumber);
            return ResponseEntity.ok(transaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}