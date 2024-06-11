package org.example.client_service.models.servicesAgence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_account_id", referencedColumnName = "paymentAccountId")
    private ComptePaiement comptePaiement;
    @OneToOne(mappedBy = "facture",fetch = FetchType.LAZY)
    @JsonManagedReference(value = "facture-transaction")
    private Transaction transaction;
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "recap", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "recap-impaye")
    private Impaye impaye;
    @OneToOne(mappedBy = "recap_recharge", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "recap-recharge")
    private Recharge recharge;
    @OneToOne(mappedBy = "recap_donation", fetch= FetchType.LAZY)
    @JsonManagedReference(value = "recap-donation")
    private Donation donation;


}


