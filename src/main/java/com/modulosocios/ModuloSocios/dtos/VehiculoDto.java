
package com.modulosocios.ModuloSocios.dtos;

import lombok.Data;

/**
 *
 * @author anima
 */
@Data
public class VehiculoDto {
    
    private Integer id;
    
    private Integer socioid;
    
    private String marca;
    
    private String modelo;
    
    private Integer capacidad;
    
    private Boolean habilitadoEquipaje;
    
    private Boolean permiteMascotas;
    
    private String matricula;
    
    private String soat;
    
    private String tecnomecanica;
         
    private String estadoverificacion;
    
    private String adjuntodocumentos;
}
