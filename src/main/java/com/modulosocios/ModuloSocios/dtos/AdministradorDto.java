package com.modulosocios.ModuloSocios.dtos;

import java.util.Date;
import java.util.List;

import com.modulosocios.ModuloSocios.model.Administrador;
import com.modulosocios.ModuloSocios.model.Socio;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDto {
    public AdministradorDto(Administrador administrador) {
    }

    private Integer id_administrador_fk;

    private String nombreAdmin;

    private String correoElectronicoAdmin;

    private String telefonoAdmin;

    private String nombreusuarioAdmin;

    private String contrasenaAdmin;

    private Date fechaRegistroAdmin;

    private List<Socio> socios;

}
