package org.example.client_service.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class OTPToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String verificationCode ;

    private Long ExperationDate ;
    @JsonIgnore
    @OneToOne
    private Client client ;

}
