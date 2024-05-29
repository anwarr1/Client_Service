package org.example.client_service.controllers;


import org.example.client_service.models.Impaye;
import org.example.client_service.service.ImpayeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/impayes")
public class ImpayeController {

    private final ImpayeService impayeService;

    public ImpayeController(ImpayeService impayeService) {
        this.impayeService = impayeService;
    }

    @GetMapping()
    public List<Impaye> getImpayesByDebtCode(@RequestParam("creance_id") Long creance_id, @RequestParam("client_id") Long client_id) {
        return impayeService.findCreanceImpayes(creance_id, client_id);
    }
}