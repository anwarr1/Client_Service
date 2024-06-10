package org.example.client_service.repository;

import org.example.client_service.models.servicesAgence.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {

}
