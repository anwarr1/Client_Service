package org.example.client_service.service;

import org.example.client_service.models.*;
import org.example.client_service.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

;

@Service
public class CmiService {

    public CmiService(CreancierRepository creancierRepository, CreanceRepository creanceRepository, ImpayeRepository impayeRepository) {
        this.creancierRepository = creancierRepository;
        this.creanceRepository = creanceRepository;
        this.impayeRepository = impayeRepository;
    }

    private CreancierRepository creancierRepository;

    private CreanceRepository creanceRepository;

    private FactureRepository factureRepository;

    private TransactionRepository transactionRepository;

    private FormRepository formRepository;
    private ImpayeRepository impayeRepository;

    private ClientRepository clientRepository;

    private ComptePaiementRepository comptePaiementRepository;


    public List<Creancier> getAllCreanciers() {
        return creancierRepository.findAll();
    }


    public Form getFormByCreanceId(Long creanceId) {
        return formRepository.findById(creanceId).orElse(null);
    }

    public List<Impaye> getImpayesByCreanceId(Long creanceId, Long clientId) {
        Creance creance = creanceRepository.findById(creanceId).orElse(null);
        if (creance != null) {
            return impayeRepository.findByCreance(creanceId ,clientId);
        }
        return null;
    }

    public boolean confirmePayer(Long impayeId, String phoneNumber) {
        Optional<Client> client = clientRepository.findByTelephone(phoneNumber);
        if (client == null) {
            return false;
        }

        Impaye impaye = impayeRepository.findById(impayeId).orElse(null);
        if (impaye == null) {
            return false;
        }

        ComptePaiement account = client.get().getComptePaiement();
        if (account.getAccountBalance() >= impaye.getMontant()) {
            account.setAccountBalance(account.getAccountBalance() - impaye.getMontant());
            comptePaiementRepository.save(account);
            impayeRepository.delete(impaye);

            // Créer une transaction
            Transaction transaction = new Transaction();
            transaction.setMontant(impaye.getMontant());
            transaction.setStatus("Payment");
            transaction.setComptePaiement(account);
            transactionRepository.save(transaction);

            return true;
        }

        return false;
    }
}