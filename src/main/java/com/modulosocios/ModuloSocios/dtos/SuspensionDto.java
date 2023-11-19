package com.modulosocios.ModuloSocios.dtos;

import java.util.Date;

import com.modulosocios.ModuloSocios.model.Socio;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuspensionDto {

    private Integer id;

    private Integer socioId;

    private Date fechaHoraSuspension;

    private String motivo;

    private String estado;

    private Socio socio;
}
