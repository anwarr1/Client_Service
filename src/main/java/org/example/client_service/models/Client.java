package org.example.client_service.models;

import com.fasterxml.jackson.annotation.*;
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
public class Client extends User {


    @OneToOne
    OTPToken otpToken;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "client-compte")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    @JsonBackReference(value = "bank")
    private Bank bank;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "bank-account")
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "client-impaye")
    private List<Impaye> impayes;

}
