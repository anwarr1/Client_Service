package org.example.client_service.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.client_service.models.Client;
import org.example.client_service.models.OTPToken;
import org.example.client_service.repository.OTPTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    private OTPTokenRepository otpTokenRepository;

    public OtpService(OTPTokenRepository otpTokenRepository) {
        this.otpTokenRepository = otpTokenRepository;
    }

    public OTPToken generateOTP(Client client) {
        OTPToken token = new OTPToken();
        token.setExperationDate(System.currentTimeMillis() + 30000L);
        token.setVerificationCode(RandomStringUtils.randomNumeric(6));
        token.setClient(client);
        client.setOtpToken(token);
        otpTokenRepository.save(token);
        return token;
    }

    public Boolean ValidateOTP(String otpClient, OTPToken otpGenerated) {
        Long time1 = otpGenerated.getExperationDate();
        Long time2 = Instant.now().toEpochMilli();
        System.out.println("time1   " + time1 + "time2   " +time2);
        if ( time1 > time2 ) {

            if (otpClient.equals(otpGenerated.getVerificationCode())) {
                Client client = otpGenerated.getClient();
                client.setOtpToken(null);
                otpTokenRepository.delete(otpGenerated);
                return true;
            } else {
                return false;
            }

        } else {
            Client client = otpGenerated.getClient();
            client.setOtpToken(null);
            otpTokenRepository.delete(otpGenerated);
            return false;
        }

    }

}
