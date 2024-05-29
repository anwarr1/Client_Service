package org.example.client_service.repository;

import org.example.client_service.models.Creance;
import org.example.client_service.models.Impaye;
import org.example.client_service.models.servicesAgence.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImpayeRepository extends JpaRepository<Impaye, Long> {
    @Query("SELECT i FROM Impaye i WHERE i.creance.codeDebt = :id_creance AND i.client.id = :client_id AND i.paid= false")
    List<Impaye> findByCreance(@Param("id_creance") Long creanceId, @Param("client_id") Long clientId);
}
