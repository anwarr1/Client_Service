package org.example.client_service.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.client_service.models.BankAccount;
import org.example.client_service.models.ComptePaiement;
import org.example.client_service.models.Impaye;
import org.example.client_service.models.Transaction;
import org.example.client_service.enums.TransactionStatus;
import org.example.client_service.models.servicesAgence.Facture;
import org.example.client_service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    private ImpayeRepository impayeRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;


    public Transaction confirmerPaiementAcc(Long impayeId, String phoneNumber) throws IllegalArgumentException {
        System.out.println("phoneNumber: " + phoneNumber + "impayeId: " + impayeId);
        ComptePaiement comptePaiement = comptePaiementRepository.findByClientTel(phoneNumber);
        Optional<Impaye> impaye = impayeRepository.findById(impayeId);
        BankAccount bankAccount = bankAccountRepository.findByClientTel(phoneNumber);

        if (comptePaiement.getAccountBalance() >= impaye.get().getMontant()) {
            // Update the account balance
            comptePaiement.setAccountBalance(comptePaiement.getAccountBalance() - impaye.get().getMontant());
            bankAccount.setBalance(bankAccount.getBalance() - impaye.get().getMontant());

            // Mark the invoice as paid
            impaye.get().setPaid(true);

            // Create the Facture from the Impaye
            Facture facture = Facture.builder()
                    .amount(impaye.get().getMontant())
                    .description(impaye.get().getDescription())
                    .paid(true)
                    .dueDate(LocalDate.now())
                    .comptePaiement(comptePaiement)
                    .build();

            // Save the Facture before creating the Transaction
            facture = factureRepository.save(facture);

            // Create the Transaction
            Transaction transaction = Transaction.builder()
                    .facture(facture)
                    .montant(facture.getAmount())
                    .date(new Date())
                    .status(String.valueOf(TransactionStatus.SUCCEEDED))
                    .comptePaiement(comptePaiement)
                    .build();

            // Save the Transaction
            transaction = transactionRepository.save(transaction);


            // Update the Facture with the Transaction
            facture.setTransaction(transaction);
            factureRepository.save(facture);
            impaye.get().setRecap(facture);

            // Add the transaction to the ComptePaiement's transaction list
            comptePaiement.getTransactions().add(transaction);

            // Save all entities
            comptePaiementRepository.save(comptePaiement);
            impayeRepository.save(impaye.get());
            bankAccountRepository.save(bankAccount);

            return transaction;
        }
        return null;
    }

    public Optional<Facture> findFactureByIdAndCustomerId(Long id, Long customerId) {
        return factureRepository.findByIdAndCustomerId(id, customerId);
    }
}