package org.example.client_service.controllers;


import org.example.client_service.models.BankAccount;
import org.example.client_service.repository.BankAccountRepository;
import org.example.client_service.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccountById(@PathVariable Long id) {
        return bankAccountService.getBankAccountById(id);
    }

    @PostMapping("/client/{clientId}")
    public BankAccount createBankAccount(@PathVariable Long clientId, @RequestBody BankAccount bankAccount) {
        return bankAccountService.createBankAccount(clientId, bankAccount);
    }

    @PutMapping("/{id}")
    public BankAccount updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccountDetails) {
        return bankAccountService.updateBankAccount(id, bankAccountDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
    }

    @GetMapping("find/{tel}")
    public BankAccount getBankAccountByTel(@PathVariable String tel) {
        return bankAccountRepository.findByClientTel(tel);
    }
}
