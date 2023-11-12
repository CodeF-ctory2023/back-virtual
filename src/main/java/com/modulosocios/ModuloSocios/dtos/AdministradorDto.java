/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modulosocios.ModuloSocios.dtos;

import java.util.Date;

import com.modulosocios.ModuloSocios.model.Administrador;

import lombok.*;

/**
 *
 * @author anima
 */
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

}
