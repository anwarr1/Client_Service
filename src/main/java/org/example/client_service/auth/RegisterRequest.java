package org.example.client_service.auth;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.client_service.models.Role;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
//    private String username;
    private String password;
    private LocalDate createdDate;
    private  Role role;

}
