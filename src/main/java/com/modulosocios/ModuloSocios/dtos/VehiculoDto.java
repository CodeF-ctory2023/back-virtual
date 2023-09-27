
package com.modulosocios.ModuloSocios.dtos;

import lombok.*;

/**
 *
 * @author anima
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDto {

    private Integer id;

    private Integer id_vehiculo_fk;

    private String marca;

    private String modelo;

    private Integer capacidad;

    private Boolean habilitadoEquipaje;

    private Boolean permiteMascotas;

    private String matricula;

    private String soat;

    private String tecnomecanica;

    private String estadoVerificacion;

    private String adjuntoDocumentos;
}
