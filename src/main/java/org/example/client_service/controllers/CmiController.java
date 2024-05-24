package org.example.client_service.controllers;


import org.example.client_service.models.Creancier;
import org.example.client_service.models.Form;
import org.example.client_service.models.Impaye;
import org.example.client_service.service.CmiService;
import org.example.client_service.service.CreancierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cmi")

public class CmiController {

    @Autowired
    private CmiService cmiService;
    @Autowired

    private  CreancierService creancierService;

    public CmiController(CmiService cmiService, CreancierService creancierService) {
        this.cmiService = cmiService;
        this.creancierService = creancierService;
    }

    @GetMapping("/creanciers")
    public List<Creancier> getAllCreanciers() {
        return cmiService.getAllCreanciers();
    }

    @GetMapping("/forms/{creanceId}")
    public Form getFormByCreanceId(@PathVariable Long creanceId) {
        return cmiService.getFormByCreanceId(creanceId);
    }

    @GetMapping("/{code}")
    public Creancier getCreancierByCode(@PathVariable Long code) {

        return creancierService.getcreancierByCode(code);
    }
    @GetMapping("/impayes/{creanceId}")
    public List<Impaye> getImpayesByCreanceId(@PathVariable Long creanceId) {
        return cmiService.getImpayesByCreanceId(creanceId);
    }

    @PostMapping("/confirmePayer")
    public boolean confirmePayer(@RequestParam Long impayeId, @RequestParam String phoneNumber) {
        return cmiService.confirmePayer(impayeId, phoneNumber);
    }
}