package org.example.client_service.repository;

import org.example.client_service.models.Transaction;
import org.example.client_service.models.servicesAgence.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
