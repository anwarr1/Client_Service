package org.example.client_service.repository;

import org.example.client_service.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query("SELECT b FROM BankAccount b WHERE b.client.telephone = :tel")
    BankAccount findByClientTel(String tel);
    Optional<BankAccount> findByAccountNumber(String accountNumber);
}
