package com.modulosocios.ModuloSocios.enums;

public enum EstadoVerificacionEnum {
    PENDIENTE("PENDIENTE"), // Cuando un socio se crea, pasa a estado PENDIENTE
    SUSPENDIDO("SUSPENDIDO"), // Un socio está SUSPENDIDO cuando comente alguna falta y queda inhabilitado
    ACEPTADO("ACEPTADO"), // Un socio es ACEPTADO, cuando tiene toda su papeleria al dia
    RETIRADO("RETIRADO"), // Un socio está RETIRADO, cuando se pretende elimiar del sistema, lo cual se
                          // hará en un periodo vigente
    REVOCADO("REVOCADO");// Un socio es revocado, caundo se le ha levantado una suspension

    private String estado;

    EstadoVerificacionEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
