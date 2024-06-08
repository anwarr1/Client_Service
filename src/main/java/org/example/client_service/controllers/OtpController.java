package org.example.client_service.controllers;


import org.example.client_service.models.Client;
import org.example.client_service.models.Creancier;
import org.example.client_service.models.OTPToken;
import org.example.client_service.service.ClientService;
import org.example.client_service.service.OtpService;
import org.example.client_service.service.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/otp")
@CrossOrigin(origins = "http://localhost:4200")
public class OtpController {

    @Autowired
    OtpService otpService;
    @Autowired
    SmsSender smsSender;


    @Autowired
    ClientService clientService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/{id_client}")
    public void GenerateOtpPayement(@PathVariable Long id_client) {
        Optional<Client> clientOptional= clientService.getClientById(id_client);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            OTPToken otpToken = otpService.generateOTP(client);
            client.setOtpToken(otpToken);
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


}
