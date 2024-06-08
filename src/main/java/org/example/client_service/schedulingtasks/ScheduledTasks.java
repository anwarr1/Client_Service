package org.example.client_service.schedulingtasks;

import org.example.client_service.models.Client;
import org.example.client_service.models.Creancier;
import org.example.client_service.models.Impaye;
import org.example.client_service.repository.ClientRepository;
import org.example.client_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Component
public class ScheduledTasks {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 8 * * ?") // Exécuter tous les jours à minuit
    public void sendInvoiceReminders() {
        List<Client> clients = clientRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Client client : clients) {
            for (Creancier creancier : client.getFavoriteCreanciers()) {

                for (Impaye impaye : creancier.getClient().getImpayes()) {
                    if (impaye.getDueDate().isEqual(today.plusDays(1))) {
                        // Envoyer un rappel un jour avant la date d'échéance
                        emailService.sendSimpleMessage(client.getEmail(),
                                "Rappel de facture",
                                "Votre facture " + impaye.getDescription() +
                                        " est due demain (" + impaye.getDueDate() + ").");
                    }
                }
            }
        }
    }

//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//    @Scheduled(fixedRate = 60000) // Exécuter toutes les minutes
//    public void sendMessage() {
//        System.out.println("Test envoie chaque minute - " + dateFormat.format(new Date()));
//        emailService.sendSimpleMessage("maryamouhdan@gmail.com",
//                "Rappel de facture",
//                " Test envoie chaque minute");
//    }
}
