package org.example.client_service.repository;

import org.example.client_service.models.servicesAgence.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RechargeRepository extends JpaRepository<Recharge, Long> {
}
