package com.modulosocios.ModuloSocios.enums;

public enum EstadoVerificacionEnum {
    PENDIENTE("PENDIENTE"),
    SUSPENDIDO("SUSPENDIDO"),
    ACEPTADO("ACEPTADO"),
    RETIRADO("RETIRADO");

    private String estado;

    EstadoVerificacionEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
