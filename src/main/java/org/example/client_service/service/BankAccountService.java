package org.example.client_service.service;



import org.example.client_service.models.BankAccount;
import org.example.client_service.models.Client;
import org.example.client_service.repository.BankAccountRepository;
import org.example.client_service.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    public BankAccount createBankAccount(Long clientId, BankAccount bankAccount) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            bankAccount.setClient(client);
            return bankAccountRepository.save(bankAccount);
        }
        return null;
    }

    public BankAccount updateBankAccount(Long id, BankAccount bankAccountDetails) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        if (bankAccount != null) {
            bankAccount.setAccountNumber(bankAccountDetails.getAccountNumber());
            bankAccount.setBalance(bankAccountDetails.getBalance());
            return bankAccountRepository.save(bankAccount);
        }
        return null;
    }

    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
}