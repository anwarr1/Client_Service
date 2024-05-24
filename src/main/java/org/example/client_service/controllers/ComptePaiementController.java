package org.example.client_service.controllers;




import java.util.List;

import org.example.client_service.dto.PaymentAccountCreationRequest;
import org.example.client_service.excep.ComptePaiementException;
import org.example.client_service.models.ComptePaiement;
import org.example.client_service.service.ApiResponse;
import org.example.client_service.service.ComptePaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/payment-accounts"})
public class ComptePaiementController {
    @Autowired
    private ComptePaiementService comptePaiementService;

    public ComptePaiementController() {
    }

    @GetMapping
    public ResponseEntity<List<ComptePaiement>> getAll() {
        List<ComptePaiement> comptePaiements = this.comptePaiementService.findAll();
        return ResponseEntity.ok(comptePaiements);
    }

    @PostMapping({"/create"})
    public ResponseEntity<?> createComptePaiement(@RequestBody PaymentAccountCreationRequest request) {
        try {
            ComptePaiement comptePaiement = this.comptePaiementService.createComptePaiement(request);
            String message = "Payment account created successfully.";
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(message, comptePaiement));
        } catch (ComptePaiementException var4) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create payment account: " + var4.getMessage());
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<?> updateComptePaiement(@PathVariable Long id, @RequestBody ComptePaiement updatedAccount) {
        try {
            ComptePaiement comptePaiement = this.comptePaiementService.update(id, updatedAccount);
            String message = "Payment account updated successfully.";
            return ResponseEntity.ok(new ApiResponse(message, comptePaiement));
        } catch (ComptePaiementException var5) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment account not found: " + var5.getMessage());
        }
    }

    @GetMapping({"/{id}/balance"})
    public ResponseEntity<?> getAccountBalance(@PathVariable Long id) {
        try {
            double balance = this.comptePaiementService.getAccountBalance(id);
            return ResponseEntity.ok(new ApiResponse("Account balance retrieved successfully.", balance));
        } catch (ComptePaiementException var4) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment account not found: " + var4.getMessage());
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteComptePaiement(@PathVariable Long id) {
        try {
            ComptePaiement deletedAccount = this.comptePaiementService.delete(id);
            String message = "Payment account deleted successfully.";
            return ResponseEntity.ok(new ApiResponse(message, deletedAccount));
        } catch (ComptePaiementException var4) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment account not found: " + var4.getMessage());
        }
    }
}
