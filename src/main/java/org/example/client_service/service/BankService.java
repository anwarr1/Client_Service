package org.example.client_service.service;

import org.example.client_service.models.Bank;
import org.example.client_service.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank getBankById(Long id) {
        return bankRepository.findById(id).orElse(null);
    }

    public Bank createBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank updateBank(Long id, Bank bankDetails) {
        Bank bank = bankRepository.findById(id).orElse(null);
        if (bank != null) {
            bank.setName(bankDetails.getName());
            bank.setAddress(bankDetails.getAddress());
            return bankRepository.save(bank);
        }
        return null;
    }

    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }
}