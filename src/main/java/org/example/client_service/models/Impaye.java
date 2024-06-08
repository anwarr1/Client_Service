package org.example.client_service.models;


        import com.fasterxml.jackson.annotation.JsonBackReference;
        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
        import org.example.client_service.models.servicesAgence.Facture;

        import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Impaye {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double montant;
    private String type;
    private LocalDate dueDate;
    @ManyToOne
    @JoinColumn(name = "creance_id")
    @JsonBackReference
    private Creance creance;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
    private boolean paid = false;
    @OneToOne
    @JsonBackReference
    private Facture recap;

    // getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}