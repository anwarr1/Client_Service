package org.example.client_service.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FormService {

    public Map<String, Object> getFormByDebtCode(String debtCode) {

        return Map.of("field1", "text", "field2", "number");
    }
}