package org.example.client_service.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("agent")
public class Agent extends User {
    private String cin;
    String num_immatriculation;
    String num_patente;
    Date date_naissance;


//    @OneToMany
//    List<PieceJointe> pieces_jointes;

}

