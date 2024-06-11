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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Donation {
    private double amount;
    private String nom_donateur;
    private boolean paid;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch= FetchType.LAZY)
    @JsonBackReference(value = "recap-donation")
    private Facture recap_donation;


}
