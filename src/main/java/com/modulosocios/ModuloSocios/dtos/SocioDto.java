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
public class SocioDto {

    private Integer id;

    private String nombre;

    private String correoElectronico;

    private String telefono;

    private String licenciaConducir;

    private String documentoIdentidad;

    private String ciudad;

    private String estadoVerificacion;

    private Date fechaRegistro;

    private Date fechaVerificacion;

    private Date fechaSuspension;

    private String motivoSuspension;

    private Boolean pendientedeVerificacion;

    private String correoNotificacion;

    private Integer administradorId;

    private String contrasena;

    private VehiculoDto vehiculos;

    private AdministradorDto administrador;

}
