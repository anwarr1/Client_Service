package org.example.client_service.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client_service.models.servicesAgence.ServiceAgence;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity

public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private byte[] image;
    @OneToMany(cascade = CascadeType.ALL)
    List<ServiceAgence> serviceAgenceList = new ArrayList<>();

}
