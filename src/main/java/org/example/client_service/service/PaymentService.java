package org.example.client_service.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.client_service.models.ComptePaiement;
import org.example.client_service.models.Transaction;
import org.example.client_service.enums.TransactionStatus;
import org.example.client_service.models.servicesAgence.Facture;
import org.example.client_service.repository.ComptePaiementRepository;
import org.example.client_service.repository.FactureRepository;
import org.example.client_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class PaymentService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private ComptePaiementRepository comptePaiementRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public Transaction confirmerPaiementAcc(Long factureId, String phoneNumber) throws IllegalArgumentException {
        Optional<Facture> factureOpt = factureRepository.findById(factureId);
        if (factureOpt.isPresent()) {
            Facture facture = factureOpt.get();
            ComptePaiement comptePaiement = facture.getTransaction().getComptePaiement();

            if (comptePaiement != null && comptePaiement.getPhoneNumber().equals(phoneNumber) &&
                    comptePaiement.getAccountBalance() >= facture.getAmount() && !facture.isPaid()) {

                // Mettre à jour le solde du compte de paiement
                comptePaiement.setAccountBalance(comptePaiement.getAccountBalance() - facture.getAmount());

                // Marquer la facture comme payée
                facture.setPaid(true);

                // Créer et enregistrer la transaction
                Transaction transaction = Transaction.builder()
                        .facture(facture)
                        .montant(facture.getAmount())
                        .date(new Date())
                        .status(String.valueOf(TransactionStatus.SUCCEEDED))
                        .comptePaiement(comptePaiement)
                        .build();

                comptePaiement.getTransactions().add(transaction);

                comptePaiementRepository.save(comptePaiement);
                factureRepository.save(facture);
                return transactionRepository.save(transaction);
            }
        }
        throw new IllegalArgumentException("Erreur lors de la confirmation du paiement");
    }
    public Optional<Facture> findFactureByIdAndCustomerId(Long id, Long customerId) {
        return factureRepository.findByIdAndCustomerId(id, customerId);
    }
}