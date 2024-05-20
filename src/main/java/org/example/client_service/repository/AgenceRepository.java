package org.example.client_service.repository;

import org.example.client_service.models.Agence;
import org.example.client_service.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenceRepository extends JpaRepository<Agence , Long> {
}
