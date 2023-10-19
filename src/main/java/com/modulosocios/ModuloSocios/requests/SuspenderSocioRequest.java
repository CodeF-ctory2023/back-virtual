package com.modulosocios.ModuloSocios.requests;

import lombok.Data;

@Data
public class SuspenderSocioRequest {
    private Integer id;
    private String motivo;
}
