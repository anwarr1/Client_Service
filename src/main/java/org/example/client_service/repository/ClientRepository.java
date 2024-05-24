package org.example.client_service.repository;

import org.example.client_service.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByTelephone(String telephone);
}
