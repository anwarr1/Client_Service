package org.example.client_service.models.servicesAgence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client_service.models.ComptePaiement;
import org.example.client_service.models.Transaction;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@Data
@Entity
public class Facture   {
    private Long customerId;

    private double amount;
    private String description;
    private boolean paid;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "payment_account_id", referencedColumnName = "paymentAccountId")
    private ComptePaiement comptePaiement;
    @OneToOne(mappedBy = "facture")
    private Transaction transaction;
    @Id
    @GeneratedValue
    private Long id;

}


