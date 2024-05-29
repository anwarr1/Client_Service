package org.example.client_service.models.servicesAgence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;
import org.example.client_service.models.ComptePaiement;
import org.example.client_service.models.Impaye;
import org.example.client_service.models.Transaction;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Facture   {
    private Long customerId;

    private double amount;
    private String description;
    private boolean paid;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "payment_account_id", referencedColumnName = "paymentAccountId")
    @JsonBackReference
    private ComptePaiement comptePaiement;
    @OneToOne(mappedBy = "facture")
    @JsonBackReference
    private Transaction transaction;
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "recap")
    @JsonBackReference
    private Impaye impaye;

}


