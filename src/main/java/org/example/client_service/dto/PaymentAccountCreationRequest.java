package org.example.client_service.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.client_service.enums.TypeHissab;

public class PaymentAccountCreationRequest {
    @Enumerated(EnumType.STRING)
    TypeHissab typeHissab;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;


    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public TypeHissab getTypeHissab() {
        return typeHissab;
    }

    public void setTypeHissab(TypeHissab typeHissab) {
        this.typeHissab = typeHissab;
    }
}