package org.example.client_service.repository;

import org.example.client_service.models.Client;
import org.example.client_service.models.Creancier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreancierRepository extends JpaRepository<Creancier,Long> {
    Creancier findBycodeCreditor(long code);

}
