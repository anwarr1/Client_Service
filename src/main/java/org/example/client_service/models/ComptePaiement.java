package org.example.client_service.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client_service.enums.TypeHissab;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ComptePaiement {

    @Enumerated(EnumType.STRING)

    TypeHissab typeHissab;
    @OneToOne(mappedBy = "comptePaiement")
    Client client;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
