package org.example.client_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class CreditCard {

    @Id
    private String creditCardNumber;

    private int cvv;

    private LocalDate expirationDate, creationDate;

    private double balance;

    @PrePersist
    public void init() {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++)
            sb.append(
                    random.nextInt(10)
            );
        creditCardNumber = sb.toString();

        random = new Random();
        cvv = random.nextInt(900) + 100;

        creationDate = LocalDate.now();
        expirationDate = creationDate.plusYears(5);

    }

}