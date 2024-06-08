package org.example.client_service.service;


import org.example.client_service.models.Client;
import org.example.client_service.models.Impaye;
import org.example.client_service.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationImpayeService {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 8 * * ?") // Exécution quotidienne à 08:00
    public void sendReminderEmails() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            for (Impaye impaye : client.getImpayes()) {
                if (impaye.getDueDate().equals(tomorrow)) {
                    String subject = "Rappel de facture";
                    String text = "Votre facture " + impaye.getCreance().getDebtName() + " arrive à échéance demain. Merci de la payer avant la date limite.";
                    emailService.sendSimpleMessage(client.getEmail(), subject, text);
                }
            }
        }
    }

}
