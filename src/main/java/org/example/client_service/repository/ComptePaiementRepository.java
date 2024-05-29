package org.example.client_service.repository;

import org.example.client_service.models.Client;
import org.example.client_service.models.ComptePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComptePaiementRepository extends JpaRepository<ComptePaiement,Long> {
}
