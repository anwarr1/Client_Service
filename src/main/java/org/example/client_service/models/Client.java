package org.example.client_service.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client extends User {
    String telephone;
    Boolean is_first_time = true;
    @OneToOne
    ComptePaiement comptePaiement;
//    @OneToMany(cascade = CascadeType.ALL)
//    List<PieceJointe> pieces_jointes = new ArrayList<>();

}
