package org.example.client_service.service;

import org.example.client_service.dto.PaymentAccountCreationRequest;
import org.example.client_service.excep.ComptePaiementException;
import org.example.client_service.models.Client;
import org.example.client_service.models.ComptePaiement;
import org.example.client_service.models.Transaction;
import org.example.client_service.models.servicesAgence.Facture;
import org.example.client_service.repository.ClientRepository;
import org.example.client_service.repository.ComptePaiementRepository;
import org.example.client_service.repository.FactureRepository;
import org.example.client_service.repository.TransactionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;


@Service
public class ComptePaiementService {

    @Autowired
    private ComptePaiementRepository comptePaiementRepository;
    @Autowired
    private ClientRepository clientRepository;

    public ComptePaiementService(ComptePaiementRepository comptePaiementRepository, ClientRepository clientRepository) {
        this.comptePaiementRepository = comptePaiementRepository;
        this.clientRepository = clientRepository;
    }

    public ComptePaiementService() {
    }

    public ComptePaiement createComptePaiement(PaymentAccountCreationRequest request) {
        Logger logger = LoggerFactory.getLogger(ComptePaiementService.class);
        logger.info("Recherche du client avec le numéro de téléphone: " + request.getPhoneNumber());

        Client client = this.clientRepository.findByTelephone(request.getPhoneNumber())
                .orElseThrow(() -> new ComptePaiementException("Aucun client associé à ce numéro de téléphone n'a été trouvé."));

        logger.info("Résultat de la recherche du client: " + client);
        System.out.println("Ana compte paiement " );

        System.out.println(client.getComptePaiement() );
        if (client.getComptePaiement() != null) {
            System.out.println("Ana compte paiement "+client.getComptePaiement() );
            System.out.println("Ana compte paiement " +client);
            throw new ComptePaiementException("Le client a déjà un compte de paiement.");
        }

        ComptePaiement comptePaiement = new ComptePaiement();
        comptePaiement.setTypeHissab(request.getTypeHissab());
        comptePaiement.setClient(client);
        comptePaiement.setCreatedDate(LocalDate.now());
        comptePaiement.setAccountBalance(0.0);
        comptePaiement.setPhoneNumber(client.getTelephone());
        client.setComptePaiement(comptePaiement);

        return this.comptePaiementRepository.save(comptePaiement);
    }

    @Transactional
    public List<ComptePaiement> findAll() {
        return this.comptePaiementRepository.findAll();
    }

    @Transactional
    public ComptePaiement update(Long id, ComptePaiement updatedAccount) {
        Logger logger = (Logger) LoggerFactory.getLogger(ComptePaiementService.class);
        Optional<ComptePaiement> comptePaiementOptional = this.comptePaiementRepository.findById(id);
        if (comptePaiementOptional.isPresent()) {
            ComptePaiement existingAccount = (ComptePaiement)comptePaiementOptional.get();
            logger.info("Updating account: " + String.valueOf(existingAccount));
            existingAccount.setAccountBalance(updatedAccount.getAccountBalance());
            existingAccount.setCreatedDate(updatedAccount.getCreatedDate());
            existingAccount.setBankName(updatedAccount.getBankName());
            existingAccount.setPhoneNumber(updatedAccount.getPhoneNumber());
            if (updatedAccount.getClient() != null) {
                Optional<Client> clientOptional = this.clientRepository.findById(updatedAccount.getClient().getId());
                if (clientOptional.isPresent()) {
                    existingAccount.setClient((Client)clientOptional.get());
                }
            }

            ComptePaiement savedAccount = (ComptePaiement)this.comptePaiementRepository.save(existingAccount);
            logger.info("Updated account: " + String.valueOf(savedAccount));
            return savedAccount;
        } else {
            throw new ComptePaiementException("Payment account with id " + id + " not found.");
        }
    }

    @Transactional
    public ComptePaiement delete(Long id) {
        Optional<ComptePaiement> comptePaiementOptional = this.comptePaiementRepository.findById(id);
        if (comptePaiementOptional.isPresent()) {
            ComptePaiement comptePaiement = (ComptePaiement)comptePaiementOptional.get();
            Client client = comptePaiement.getClient();
            client.setComptePaiement((ComptePaiement)null);
            this.clientRepository.save(client);
            this.comptePaiementRepository.delete(comptePaiement);
            return comptePaiement;
        } else {
            throw new ComptePaiementException("Payment account with id " + id + " not found.");
        }
    }

    @Transactional(
            readOnly = true
    )
    public double getAccountBalance(Long id) {
        Optional<ComptePaiement> comptePaiementOptional = this.comptePaiementRepository.findById(id);
        if (comptePaiementOptional.isPresent()) {
            return ((ComptePaiement)comptePaiementOptional.get()).getAccountBalance();
        } else {
            throw new ComptePaiementException("Payment account with id " + id + " not found.");
        }
    }
}