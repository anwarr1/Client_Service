package org.example.client_service.models;

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
public class Creancier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeCreditor;
    private String creditorName;
    private String category;
    @OneToMany(mappedBy = "creditor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "creditor-creance")
    private List<Creance> listCreances;
    private String image;


}