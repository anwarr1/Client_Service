package org.example.client_service.models;




import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factureId")
    @JsonBackReference(value = "facture-transaction")
    private Facture facture;
    private double montant;
    private Date date;
    private String status;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_paiement_id")
    @JsonBackReference(value = "comptePaiement-transaction")
    private ComptePaiement comptePaiement;
}