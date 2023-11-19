package com.modulosocios.ModuloSocios.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.modulosocios.ModuloSocios.model.Vehiculo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocioSimpleDto {

    private Integer id;

    private String documentoIdentidad;

    private String nombre;

    private String correoElectronico;

    private String telefono;

    private String ciudad;

    private String licenciaConducir;

    private String pasadoJudicial;

    private String foto;

    private String estadoVerificacion;

    private Vehiculo vehiculo;

    private List<SuspensionDto> suspensiones;

}
