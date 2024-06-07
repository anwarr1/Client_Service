package org.example.client_service.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client_service.models.Client;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
public class ClientAndPieceJointRequest {
    Client client;
    MultipartFile imageUrl;
}
