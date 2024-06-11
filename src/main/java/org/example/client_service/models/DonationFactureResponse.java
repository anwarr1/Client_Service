package org.example.client_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.client_service.models.servicesAgence.Donation;
import org.example.client_service.models.servicesAgence.Facture;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationFactureResponse {
    private Donation donation;
    private Facture facture;
}
