package org.example.client_service.repository;

import org.example.client_service.models.OTPToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPTokenRepository extends JpaRepository<OTPToken, Integer> {
    @Query("select o from OTPToken o where o.client.id = :id")
    public OTPToken findByClientId(Long id);
}
