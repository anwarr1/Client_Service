package org.example.client_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PieceJointe {
    String userID;
    byte []data;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
