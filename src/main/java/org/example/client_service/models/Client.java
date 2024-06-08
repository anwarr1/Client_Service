package org.example.client_service.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("client")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client extends User {


    @OneToOne
    OTPToken otpToken;

    @OneToOne(mappedBy = "client", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private ComptePaiement comptePaiement;

    private Date birthdate;
    private String commercialRn;
    private String patent;
    private String address;
    private String cin;
    @Column(length = 50000000)
    private byte[] cin_recto;
    @Column(length = 50000000)
    private byte[] cin_verso;

    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "bank_id")
//    @JsonManagedReference
    private Bank bank;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
    private List<BankAccount> bankAccounts;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Impaye> impayes;

}
