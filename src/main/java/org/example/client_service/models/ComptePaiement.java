package org.example.client_service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.client_service.enums.TypeHissab;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComptePaiement {

    @Enumerated(EnumType.STRING)
    TypeHissab typeHissab;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long paymentAccountId;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="client_id", referencedColumnName="id")
    @JsonBackReference
    private Client client;

    private double accountBalance;
    private LocalDate createdDate;
    private String bankName;
    private String PhoneNumber;
   //@OneToMany(mappedBy = "comptePaiement",cascade = CascadeType.ALL, orphanRemoval = true)


   //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "paymentAccountId")
   @OneToMany(mappedBy = "comptePaiement", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonManagedReference
   private List<Transaction> transactions = new ArrayList<>();





}
