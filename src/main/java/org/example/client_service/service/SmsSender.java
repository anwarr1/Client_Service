package org.example.client_service.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.example.client_service.config.TwilioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    @Autowired
    private TwilioConfig twilioConfig ;

    public SmsSender(TwilioConfig twilioConfig){
        this.twilioConfig = twilioConfig;
    }

    public void sendSms(String toNumber, String tempPassword) {
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
        Message.creator(
                new PhoneNumber(toNumber),    // To phone number
                new PhoneNumber(twilioConfig.getPhoneNumber()), // From Twilio phone number
                tempPassword
        ).create();
    }
}