package org.example.client_service.models.servicesAgence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client_service.models.ComptePaiement;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Donation {
    private double amount;
    private String nom_donateur;
    private boolean paid;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JsonBackReference
    private Facture recap_donation;


}
