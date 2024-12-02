package com.colorindomeudia.colorindomeudia.util;

public enum StatusPay {  PENDENTE("Pendente"),
    REALIZADO("Realizado");

    private final String status;

    // Construtor
    StatusPay(String status) {
        this.status = status;
    }

    // Método para obter o valor do status
    public String getStatus() {
        return status;
    }

}
