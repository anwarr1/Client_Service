package org.example.client_service.controllers;

import org.example.client_service.models.Client;
import org.example.client_service.models.OTPToken;
import org.example.client_service.service.FactureService;
import org.example.client_service.service.OtpService;
import org.example.client_service.service.SmsSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/client")
public class ClientController {

    SmsSender smsSender;
    OtpService otpService;
    FactureService factureService;

    public ClientController(SmsSender smsSender, OtpService otpService ,FactureService factureService) {
        this.smsSender = smsSender;
        this.otpService = otpService;
        this.factureService = factureService;
    }
//TESTED
    @PostMapping("/SendVerificationCode")
    public ResponseEntity<Client> SenderCode(@RequestBody Client client) {
        OTPToken otpToken = otpService.generateOTP(client);
        client.setOtpToken(otpToken);
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

}
