package com.example.JibiAppClient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.net.URL;

@Data
@Entity
    public class Agent {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long agent_id;

        @NotBlank(message = "First name is mandatory")
        private String firstName;

        @NotBlank(message = "Last name is mandatory")
        private String lastName;

        @Email(message = "Email should be valid")
        private String email;

        @NotBlank(message = "Phone number is mandatory")
        @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
        private String phoneNumber;

        @Lob
        private byte[] cinFront;

        @Lob
        private byte[] cinBack;

        private String username;
        private String password;

}
