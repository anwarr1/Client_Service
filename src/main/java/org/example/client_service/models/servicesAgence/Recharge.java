package org.example.client_service.models.servicesAgence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class Recharge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double amount;
    private boolean paid;
    private String tel_recharge;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "recap-recharge")
    private Facture recap_recharge;
}

