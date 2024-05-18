package org.example.client_service.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PieceJointe {
    @ManyToOne
    User user;
    byte[] data;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
