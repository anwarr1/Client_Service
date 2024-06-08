package org.example.client_service.controllers;

import lombok.AllArgsConstructor;
import org.example.client_service.models.Client;
import org.example.client_service.models.OTPToken;
import org.example.client_service.models.Transaction;
import org.example.client_service.models.servicesAgence.Donation;
import org.example.client_service.models.servicesAgence.Facture;
import org.example.client_service.models.servicesAgence.Recharge;
import org.example.client_service.repository.DonationRepository;
import org.example.client_service.repository.FactureRepository;
import org.example.client_service.repository.RechargeRepository;
import org.example.client_service.service.ClientService;
import org.example.client_service.service.OtpService;
import org.example.client_service.service.PaymentService;
import org.example.client_service.service.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/paiement")
@CrossOrigin(origins= "http://localhost:4200")
@AllArgsConstructor
public class PaiementController {

    private PaymentService paymentService;
    private FactureRepository factureRepository;
    private DonationRepository donationRepository;
    private RechargeRepository rechargeRepository;

    @GetMapping("/facture/{id}")
    public ResponseEntity<Facture> getFactureByIdAndCustomerId(
            @PathVariable Long id,
            @RequestParam Long customerId) {
        Optional<Facture> facture = paymentService.findFactureByIdAndCustomerId(id, customerId);
        return facture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/confirmer")
    public ResponseEntity<?> confirmerPaiement(@RequestParam("id_impaye") Long factureId, @RequestParam("phone") String phoneNumber) {
        try {
            System.out.println("+"+"phoneNumber: " + phoneNumber + "factureId: " + factureId);
            Transaction transaction = paymentService.confirmerPaiementAcc(factureId,"+"+phoneNumber);
            return ResponseEntity.ok(transaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/donation")
    public ResponseEntity<?> confirmerDonation(@RequestParam("montant") double montant, @RequestParam("phone") String phoneNumber,@RequestParam("id") Long id) {
        try {
            System.out.println("+"+"phoneNumber: " + phoneNumber );
            Transaction transaction = paymentService.makeDonation("+"+phoneNumber,montant,id);
            return ResponseEntity.ok(transaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/recharge")
    public ResponseEntity<?> confirmerRecharge(@RequestParam("montant") double montant, @RequestParam("phone") String phoneNumber,@RequestParam("id") Long id) {
        try {
            System.out.println("+"+"phoneNumber: " + phoneNumber );
            Transaction transaction = paymentService.Recharge("+"+phoneNumber,montant,id);
            return ResponseEntity.ok(transaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Autowired
    OtpService otpService;
    @Autowired
    SmsSender smsSender;

    @Autowired
    ClientService clientService;


    @PostMapping("/{id_client}")
    public void GenerateOtpPayement(@PathVariable Long id_client) {

        Optional<Client> clientOptional= clientService.getClientById(id_client);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            OTPToken otpToken = otpService.generateOTP(client);
//            client.setOtpToken(otpToken);
            smsSender.sendSms(client.getTelephone(), "your verification Code is : " + otpToken.getVerificationCode());
        }
    }

    @PostMapping("/{id_client}/{code}")
    public Boolean ValidateOtpPayement(@PathVariable String code, @PathVariable Long id_client) {
        Optional<Client> clientOptional= clientService.getClientById(id_client);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            return otpService.ValidateOTP(code, client.getOtpToken());
        }
        return false;
    }

    @PostMapping("/createDonation")
    public Donation createDonation(@RequestParam double montant, @RequestParam String fullname) {
        Donation donation = new Donation();
        donation.setAmount(montant);
        donation.setNom_donateur(fullname);
        return donationRepository.save(donation);
    }

    @PostMapping("/createRecharge")
    public Recharge createRecharge(@RequestParam double montant, @RequestParam String tel_recharge) {
        Recharge recharge = new Recharge();
        recharge.setAmount(montant);
        recharge.setTel_recharge(tel_recharge);
        return rechargeRepository.save(recharge);
    }

   @GetMapping("/getRecharge/{id}")
    public Recharge getRecharge(@PathVariable Long id) {
        return rechargeRepository.findById(id).orElse(null);
    }

}