package org.example.client_service.service;


import org.example.client_service.models.Impaye;
import org.example.client_service.repository.ImpayeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ImpayeService {

    @Autowired
    private  ImpayeRepository impayeRepository;

    public List<Impaye> findCreanceImpayes(Long creanceId, Long clientId) {
        return impayeRepository.findByCreance(creanceId,clientId);
    }

}