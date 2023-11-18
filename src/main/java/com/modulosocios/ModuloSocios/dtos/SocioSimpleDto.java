package com.modulosocios.ModuloSocios.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocioSimpleDto {

    private Integer id;

    private String nombre;

    private String correoElectronico;

    private String telefono;

    private String ciudad;

    private String documentoIdentidad;

    private String estadoVerificacion;

    private String licenciaConducir;

    private String pasadoJudicial;

    private String foto;

}
