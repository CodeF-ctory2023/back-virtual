package com.modulosocios.ModuloSocios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    private Integer socioid;
    
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

    
    @OneToOne
    @JoinColumn(name = "socioid", unique = true, insertable = false, updatable = false)
    @JsonIgnore
    private Socio socio;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<RetiroVehiculo> retiros;
}
