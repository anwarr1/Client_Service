package com.example.JibiAppClient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long client_id;

    //@NotBlank(message = "First name is mandatory")
    private String firstName;

    //@NotBlank(message = "Last name is mandatory")
    private String lastName;

    //@Pattern(regexp = "\\d{14}", message = "Phone number should be 14 digits")
    private String phoneNumber;

    private String email;

    // Est ce que Client entre aussi CIN Pour enregistrer Ou juste
    /*@Lob
    private byte[] cinFront;

    @Lob
    private byte[] cinBack;
    */

    private String accountLimit;
    private String Password;
}
