
package com.modulosocios.ModuloSocios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author anima
 */
@Entity
@Table (name = "vehiculo" )
@Data
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column (name = "id" )
    private Integer id;
    
    @Column (name = "socioid" )
    private Integer id_vehiculo_fk;
    
    @Column (name = "marca" )
    private String marca;
    
    @Column (name = "modelo" )
    private String modelo;
    
    @Column (name = "capacidad" )
    private Integer capacidad;
    
    @Column (name = "habilitadoequipaje" )
    private Boolean habilitadoEquipaje;
    
    @Column (name = "permitemascotas" )
    private Boolean permiteMascotas;
    
    @Column (name = "matricula" )
    private String matricula;
    
    @Column (name = "soat" )
    private String soat;
    
    @Column (name = "tecnomecanica" )
    private String tecnomecanica;
         
    @Column (name = "estadoverificacion" )
    private String estadoVerificacion;
    
    @Column (name = "adjuntodocumentos" )
    private String adjuntoDocumentos;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", insertable = false, updatable = false, nullable = true)
    private Socios socio;
}
