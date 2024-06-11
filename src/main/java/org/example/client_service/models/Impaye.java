package org.example.client_service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.client_service.models.servicesAgence.Facture;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Impaye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double montant;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creance_id")
    @JsonBackReference(value = "creance-impaye")
    private Creance creance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "client-impaye")
    private Client client;

    private boolean paid = false;

    @OneToOne( fetch = FetchType.LAZY)
    @JsonBackReference(value = "recap-impaye")
    private Facture recap;
}
