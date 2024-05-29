package org.example.client_service.controllers;

import org.example.client_service.service.FormService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/{debtCode}")
    public Map<String, Object> getFormByDebtCode(@PathVariable String debtCode) {
        return formService.getFormByDebtCode(debtCode);
    }
}
