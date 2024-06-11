package org.example.client_service.service;

import org.example.client_service.models.Client;
import org.example.client_service.models.ComptePaiement;
import org.example.client_service.models.Transaction;
import org.example.client_service.repository.ClientRepository;
import org.example.client_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getProfessionalClientTransactions(Long clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            ComptePaiement comptePaiement = client.getComptePaiement();
            if (comptePaiement != null && client.isProfessional()) {
                return comptePaiement.getTransactions();
            }
        }
        return List.of(); // Retournez une liste vide si le client n'est pas professionnel ou s'il n'a pas de compte de paiement
    }
}
