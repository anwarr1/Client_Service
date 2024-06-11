package org.example.client_service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditor_id")
    @JsonBackReference(value = "creditor-creance")
    private Creancier creditor;

    @OneToMany(mappedBy = "creance", fetch=FetchType.LAZY)
    @JsonManagedReference(value = "creance-impaye")
    private List<Impaye> impayes;
    private String type;
}
