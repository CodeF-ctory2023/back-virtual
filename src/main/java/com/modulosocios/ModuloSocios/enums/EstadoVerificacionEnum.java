package com.modulosocios.ModuloSocios.enums;

public enum EstadoVerificacionEnum {
    PENDIENTE("PENDIENTE"),
    SUSPENDIDO("SUSPENDIDO"),
    ACEPTADO("ACEPTADO");

    String estado;

    EstadoVerificacionEnum(String estado) {
        this.estado = estado;
    }
}
