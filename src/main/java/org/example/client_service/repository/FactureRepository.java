package org.example.client_service.repository;

import org.example.client_service.models.servicesAgence.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {


    Optional<Facture> findByIdAndCustomerId(Long id, Long customerId);
}