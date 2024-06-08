package org.example.client_service.controllers;

import org.example.client_service.dto.UserChangePasswordRequest;
import org.example.client_service.models.Client;
import org.example.client_service.models.OTPToken;
import org.example.client_service.models.User;
import org.example.client_service.repository.ClientRepository;
import org.example.client_service.service.FactureService;
import org.example.client_service.service.OtpService;
import org.example.client_service.service.SmsSender;
import org.example.client_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api/client")
@CrossOrigin(origins= "http://localhost:4200")
public class ClientController {

    private final ClientRepository clientRepository;

    SmsSender smsSender;
    OtpService otpService;
    FactureService factureService;

    public ClientController(SmsSender smsSender, OtpService otpService , FactureService factureService, ClientRepository clientRepository) {
        this.smsSender = smsSender;
        this.otpService = otpService;
        this.factureService = factureService;
        this.clientRepository = clientRepository;
    }
//TESTED
    @PostMapping("/SendVerificationCode")
    public ResponseEntity<Client> SenderCode(@RequestBody Client client) {
        OTPToken otpToken = otpService.generateOTP(client);
//        client.setOtpToken(otpToken);
        smsSender.sendSms(client.getTelephone(), "your verification Code is : " + otpToken.getVerificationCode());
        return ResponseEntity.ok(client);
    }

    @PostMapping("/ValidationCode")
    public Boolean ValidationCode(@RequestBody Client client, @RequestParam("code") String code) throws IOException {
        return otpService.ValidateOTP(code, client.getOtpToken());
    }
   @PostMapping("/SendEmail")
    public void SendEmail() throws IOException {
       factureService.sendReminderEmails();
    }
   @GetMapping("/getTel/{id}")
    public String getTelById(@PathVariable Long id){
        return clientRepository.findTelById(id);
    }
    @GetMapping("/getIdByTel/{tel}")
    public Long getIdByTel(@PathVariable String tel){
        Optional<Client> client=clientRepository.findByTelephone(tel);
        return client.get().getId();
    }

}
