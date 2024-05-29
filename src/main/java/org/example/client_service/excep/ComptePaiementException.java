package org.example.client_service.excep;


public class ComptePaiementException extends RuntimeException {
    public ComptePaiementException(String message) {
        super(message);
    }
}