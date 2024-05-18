package org.example.client_service.request;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client_service.models.Client;
import org.example.client_service.models.PieceJointe;


@NoArgsConstructor
@Getter
@Setter
public class ClientAndPieceJointRequest {
    Client client;
    PieceJointe pieceJointe;
}
