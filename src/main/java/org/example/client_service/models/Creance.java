package org.example.client_service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Creance {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeDebt;
    private Double amount;
    private String debtName;
    @ManyToOne
    @JoinColumn(name = "creditor_id")
    @JsonBackReference
    private Creancier creditor;

    @OneToMany(mappedBy = "creance")
    private List<Impaye> impayes;
}
