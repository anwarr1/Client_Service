package com.example.JibiAppClient.repository;

import com.example.JibiAppClient.model.Agent;
import com.example.JibiAppClient.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
