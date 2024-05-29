package org.example.client_service.models;




import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.client_service.models.servicesAgence.Facture;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "factureId")
    private Facture facture;
    private double montant;
    private Date date;
    private String status;



    @JsonBackReference


    @ManyToOne
    @JoinColumn(name = "compte_paiement_id") // Correct usage of @JoinColumn
    private ComptePaiement comptePaiement;
}