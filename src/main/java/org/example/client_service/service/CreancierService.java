package org.example.client_service.service;

import org.example.client_service.models.Creancier;
import org.example.client_service.repository.CreancierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreancierService {

    private final CreancierRepository creancierRepository;


   @Autowired
    public CreancierService(CreancierRepository creancierRepository) {
        this.creancierRepository = creancierRepository;
    }

    public List<Creancier> getAllcreanciers() {
        return creancierRepository.findAll();
    }

    public Creancier getcreancierByCode(Long code) {
        return creancierRepository.findBycodeCreditor(code);
    }
}