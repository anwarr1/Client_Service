package org.example.client_service.repository;

import org.example.client_service.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByUsernameAndPassword(String username, String password);

}