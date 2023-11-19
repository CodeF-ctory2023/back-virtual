package com.modulosocios.ModuloSocios.dtos;

import java.util.List;

import com.modulosocios.ModuloSocios.model.RetiroVehiculo;
import com.modulosocios.ModuloSocios.model.Socio;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDto {

    private Integer id;

    private Integer socioid;

    private String nMatricula;

    private String marca;

    private String modelo;

    private Integer capacidad;

    private Boolean habilitadoEquipaje;

    private Boolean permiteMascotas;

    private String matricula;

    private String soat;

    private String tecnomecanica;

    private String adjuntoDocumentos;

    private String estadoVerificacion;

    private Socio socio;

    private List<RetiroVehiculo> retiros;
}
