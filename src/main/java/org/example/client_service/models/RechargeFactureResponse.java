package org.example.client_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.client_service.models.servicesAgence.Donation;
import org.example.client_service.models.servicesAgence.Facture;
import org.example.client_service.models.servicesAgence.Recharge;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeFactureResponse {
    private Recharge recharge;
    private Facture facture;
}
