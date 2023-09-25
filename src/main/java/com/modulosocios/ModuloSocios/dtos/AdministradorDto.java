/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modulosocios.ModuloSocios.dtos;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author anima
 */
@Data
public class AdministradorDto {

    private Integer id;

    private String nombre;

    private String correoelectronico;

    private String telefono;

    private String nombreusuario;

    private String contrasena;

    private Date fecharegistro;

}
