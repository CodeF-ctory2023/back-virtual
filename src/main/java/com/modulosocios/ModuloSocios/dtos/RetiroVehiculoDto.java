package com.modulosocios.ModuloSocios.dtos;

import java.util.Date;

import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.model.Vehiculo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetiroVehiculoDto {

    private Integer id;

    private Integer socioid;

    private Integer vehiculoid;

    private Date fechaHoraRetiro;

    private String justificacion;

    private Socio socio;

    private Vehiculo vehiculo;
}
