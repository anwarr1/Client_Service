package org.example.client_service.models;


        import com.fasterxml.jackson.annotation.JsonBackReference;
        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Impaye {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private String type;
    @ManyToOne
    @JoinColumn(name = "creance_id")
    @JsonBackReference
    private Creance creance;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;


    // getters et setters
}