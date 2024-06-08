package org.example.client_service.repository;

import org.example.client_service.models.Form;
import org.example.client_service.models.servicesAgence.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long>  {
}
