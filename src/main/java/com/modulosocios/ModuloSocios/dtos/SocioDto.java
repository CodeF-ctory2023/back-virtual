package com.modulosocios.ModuloSocios.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.modulosocios.ModuloSocios.model.Vehiculo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocioDto {

    private Integer id;

    private String documentoIdentidad;

    private String nombre;

    private String correoElectronico;

    private String telefono;

    private String ciudad;

    private String pasadoJudicial;

    private String licenciaConducir;

    private String foto;

    private String estadoVerificacion;

    private Date fechaRegistro;

    private Date fechaVerificacion;

    private Date fechaSuspension;

    private Date fechaRetiro;

    private String motivoSuspension;

    private Boolean pendientedeVerificacion;

    private String correoNotificacion;

    private Integer administradorId;

    private Vehiculo vehiculo;

    private AdministradorDto administrador;

    private List<SuspensionDto> suspensiones;

}
