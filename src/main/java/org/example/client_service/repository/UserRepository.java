package org.example.client_service.repository;

import org.example.client_service.models.Transaction;
import org.example.client_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>  {
    Optional<User> findByEmail( String email);


}
