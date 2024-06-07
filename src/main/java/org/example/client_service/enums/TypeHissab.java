package org.example.client_service.enums;

import lombok.Getter;
import lombok.Setter;

@Getter

public enum TypeHissab {
    Acc_200(200, 1),
    Acc_5000(5000, 2),

    Acc_20000(20000, 3);

    private final int value, numero;

    TypeHissab(int value, int numero) {
        this.value = value;
        this.numero = numero;
    }

}