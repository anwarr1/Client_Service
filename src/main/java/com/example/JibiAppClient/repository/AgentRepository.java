package com.example.JibiAppClient.repository;

import com.example.JibiAppClient.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository  extends JpaRepository<Agent, Long> {
    Agent findByUsernameAndPassword(String username, String password);
    Agent findByUsername(String username);
    Agent findById(long id);
}
