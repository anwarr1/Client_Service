package org.example.client_service;

import jakarta.transaction.Transactional;
import org.example.client_service.models.Client;
import org.example.client_service.models.OTPToken;
import org.example.client_service.service.ClientService;
import org.example.client_service.service.OtpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OTPServiceTest {

    @Autowired
    OtpService otpService;
    @Autowired
    ClientService clientService;
    @Test
    @Transactional
    public void testGenerateOTP() {
        Client client = clientService.getClientById(1L).get();
        OTPToken otpToken=otpService.generateOTP(client);
        System.out.println(otpToken);
    }
}
