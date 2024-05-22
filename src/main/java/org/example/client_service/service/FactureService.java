package org.example.client_service.service;

import org.example.client_service.models.servicesAgence.FactureServiceAgence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FactureService {



    private final EmailService emailService;

    @Autowired
    public FactureService( EmailService emailService) {
        this.emailService = emailService;
    }
    /*@Scheduled(fixedRate = 3600000) // Exécute toutes les heures
        public void sendReminderEmails() {
            long currentTime = System.currentTimeMillis();
            List<FactureServiceAgence> factures = factureRepository.findAll();

            for (FactureServiceAgence facture : factures) {
                if (currentTime >= facture.getReminderDate() && currentTime < facture.getExpirationDate()) {
                    emailService.sendSimpleMessage(facture.getClient().getEmail(),
                            "Rappel de facture",
                            "Votre facture avec la description: " + facture.getDescription() + " expire dans 24 heures.");
                    // Optionnel : Mettre à jour l'état de la facture pour éviter d'envoyer plusieurs rappels
                }
            }
        }*/
    public void sendReminderEmails() {

                emailService.sendSimpleMessage("maryamouhdan@gmail.com",
                        "Rappel de facture",
                        "Votre facture avec la description:  ");
                // Optionnel : Mettre à jour l'état de la facture pour éviter d'envoyer plusieurs rappels
            }

    }

