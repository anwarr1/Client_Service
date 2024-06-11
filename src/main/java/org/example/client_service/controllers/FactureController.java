package org.example.client_service.controllers;

import lombok.AllArgsConstructor;
import org.example.client_service.models.Impaye;
import org.example.client_service.models.servicesAgence.Donation;
import org.example.client_service.models.servicesAgence.Recharge;
import org.example.client_service.repository.DonationRepository;
import org.example.client_service.repository.FactureRepository;
import org.example.client_service.repository.ImpayeRepository;
import org.example.client_service.repository.RechargeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/facture")
@CrossOrigin(origins= "http://localhost:4200")
@AllArgsConstructor
public class FactureController {

    private DonationRepository donationRepository;
    private RechargeRepository rechargeRepository;
    private ImpayeRepository impayeRepository;
    private FactureRepository factureRepository;

    @GetMapping("/getDonation/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable Long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        return donation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getRecharge/{id}")
    public ResponseEntity<Recharge> getRechargeById(@PathVariable Long id) {
        Optional<Recharge> recharge = rechargeRepository.findById(id);
        return recharge.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getImpaye/{id}")
    public ResponseEntity<Impaye> getImpayeById(@PathVariable Long id) {
        Optional<Impaye> impaye = impayeRepository.findById(id);
        return impaye.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFactureById(@PathVariable Long id) {
        Optional<?> facture = factureRepository.findById(id);
        return facture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}