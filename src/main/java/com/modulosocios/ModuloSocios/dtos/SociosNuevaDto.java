package com.modulosocios.ModuloSocios.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 *
 * @author anima
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SociosNuevaDto {

    private Integer id;

    private String nombre;

    private String correoElectronico;

    private String telefono;

    private String documentoIdentidad;
    
    private String licenciaConducir;

    private String pasadoJudicial;

}
