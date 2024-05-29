package org.example.client_service.repository;

import org.example.client_service.models.ComptePaiement;
import org.example.client_service.models.Creance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreanceRepository  extends JpaRepository<Creance,Long> {

    Creance findBycodeDebt(Long code);

}

