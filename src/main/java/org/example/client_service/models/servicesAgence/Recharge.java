package org.example.client_service.models.servicesAgence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client_service.models.ComptePaiement;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Recharge {
    private double amount;
    private String description;
    private boolean paid;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "payment_account_id", referencedColumnName = "paymentAccountId")
    private ComptePaiement comptePaiement;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}

