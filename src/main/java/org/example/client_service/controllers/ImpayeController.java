package org.example.client_service.controllers;


import org.example.client_service.service.ImpayeService;
import org.springframework.web.bind.annotation.*;

        import java.util.Map;

@RestController
@RequestMapping("/impayes")
public class ImpayeController {

    private final ImpayeService impayeService;

    public ImpayeController(ImpayeService impayeService) {
        this.impayeService = impayeService;
    }

    @GetMapping("/{debtCode}")
    public Map<String, Object> getImpayesByDebtCode(@PathVariable Long debtCode) {
        return impayeService.getImpayesByDebtCode(debtCode);
    }
}