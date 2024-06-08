package org.example.client_service.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.client_service.models.BankAccount;
import org.example.client_service.models.ComptePaiement;
import org.example.client_service.models.Impaye;
import org.example.client_service.models.Transaction;
import org.example.client_service.enums.TransactionStatus;
import org.example.client_service.models.servicesAgence.Donation;
import org.example.client_service.models.servicesAgence.Facture;
import org.example.client_service.models.servicesAgence.Recharge;
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
    @Autowired
    private OtpService otpService;
    @Autowired
    private RechargeRepository rechargeRepository;
    @Autowired
    private DonationRepository donationRepository;

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

    public Transaction makeDonation(String phoneNumber, double donationAmount, Long id) throws IllegalArgumentException {
        System.out.println("phoneNumber: " + phoneNumber + " donationAmount: " + donationAmount + " id: " + id);
        ComptePaiement comptePaiement = comptePaiementRepository.findByClientTel(phoneNumber);
        Optional<Donation> donation = donationRepository.findById(id);
        donation.get().setPaid(true);
        BankAccount bankAccount = bankAccountRepository.findByClientTel(phoneNumber);

        if (comptePaiement.getAccountBalance() >= donationAmount) {
            // Update the account balance
            comptePaiement.setAccountBalance(comptePaiement.getAccountBalance() - donationAmount);
            bankAccount.setBalance(bankAccount.getBalance() - donationAmount);

            // Create the Facture for the donation
            Facture facture = Facture.builder()
                    .amount(donationAmount)
                    .description("Donation")
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

            // Add the transaction to the ComptePaiement's transaction list
            comptePaiement.getTransactions().add(transaction);
            donation.get().setRecap_donation(facture);
            // Save all entities
            donationRepository.save(donation.get());
            comptePaiementRepository.save(comptePaiement);
            bankAccountRepository.save(bankAccount);

            return transaction;
        }
        return null;
    }

    public Transaction Recharge(String phoneNumber, double montant, Long id) throws IllegalArgumentException {
        System.out.println("phoneNumber: " + phoneNumber + " donationAmount: " + montant + " id: " + id);
        ComptePaiement comptePaiement = comptePaiementRepository.findByClientTel(phoneNumber);
        BankAccount bankAccount = bankAccountRepository.findByClientTel(phoneNumber);
        Optional<Recharge> recharge= rechargeRepository.findById(id);
        recharge.get().setPaid(true);

        if (comptePaiement.getAccountBalance() >= montant) {
            // Update the account balance
            comptePaiement.setAccountBalance(comptePaiement.getAccountBalance() - montant);
            bankAccount.setBalance(bankAccount.getBalance() - montant);

            // Create the Facture for the donation
            Facture facture = Facture.builder()
                    .amount(montant)
                    .description("Donation")
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

            // Add the transaction to the ComptePaiement's transaction list
            comptePaiement.getTransactions().add(transaction);
            recharge.get().setRecap_recharge(facture);

            // Save all entities
            rechargeRepository.save(recharge.get());
            comptePaiementRepository.save(comptePaiement);
            bankAccountRepository.save(bankAccount);

            return transaction;
        }
        return null;
    }




    public Optional<Facture> findFactureByIdAndCustomerId(Long id, Long customerId) {
        return factureRepository.findByIdAndCustomerId(id, customerId);
    }
}