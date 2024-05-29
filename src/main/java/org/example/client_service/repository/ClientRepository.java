package org.example.client_service.repository;

import org.example.client_service.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByTelephone(String telephone);
    @Query("SELECT c.telephone FROM Client c WHERE c.id = :id")
    String findTelById(Long id);
    Client findByEmailAndPassword(String email, String password);
}
