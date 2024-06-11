package org.example.client_service.controllers;

import org.example.client_service.models.FinancialReport;
import org.example.client_service.models.Transaction;
import org.example.client_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins= "http://localhost:4200")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/report")
    public FinancialReport generateFinancialReport() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        double totalIncoming = transactions.stream()
                .filter(t -> t.getType().equals("incoming"))
                .mapToDouble(Transaction::getMontant)
                .sum();
        double totalOutgoing = transactions.stream()
                .filter(t -> t.getType().equals("outgoing"))
                .mapToDouble(Transaction::getMontant)
                .sum();
        return new FinancialReport(totalIncoming, totalOutgoing);
    }
    @GetMapping("/professional/{clientId}")
    public List<Transaction> getProfessionalClientTransactions(@PathVariable Long clientId) {
        return transactionService.getProfessionalClientTransactions(clientId);
    }

}
