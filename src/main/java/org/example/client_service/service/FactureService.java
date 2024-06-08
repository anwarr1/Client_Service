package org.example.client_service.service;


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
    public void sendReminderEmails() {

                emailService.sendSimpleMessage("maryamouhdan@gmail.com",
                        "Rappel de facture",
                        "Votre facture avec la description:  ");
                // Optionnel : Mettre à jour l'état de la facture pour éviter d'envoyer plusieurs rappels
            }

    }

