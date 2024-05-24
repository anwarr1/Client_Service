package org.example.client_service.repository;

import org.example.client_service.models.Creance;
import org.example.client_service.models.Impaye;
import org.example.client_service.models.servicesAgence.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImpayeRepository extends JpaRepository<Impaye, Long> {
    List<Impaye> findByCreance(Creance creance);

}
