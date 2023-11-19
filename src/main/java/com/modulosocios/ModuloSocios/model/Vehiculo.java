package com.modulosocios.ModuloSocios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "vehiculo")
@Data
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "socioid")
    private Integer socioid;

    // Campos ingresados en el formulario
    @Column(name = "matriculaNumber")
    private String matriculaNumber;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "habilitadoequipaje")
    private Boolean habilitadoEquipaje;

    @Column(name = "permitemascotas")
    private Boolean permiteMascotas;

    // Campos de tipo File
    @Column(name = "matricula")
    private String matricula;

    @Column(name = "soat")
    private String soat;

    @Column(name = "tecnomecanica")
    private String tecnomecanica;

    @Column(name = "adjuntodocumentos")
    private String adjuntoDocumentos;

    // Campos adicionales que se calculan automáticamente
    @Column(name = "estadoverificacion")
    private String estadoVerificacion;

    @OneToOne
    @JoinColumn(name = "socioid", unique = true, insertable = false, updatable = false)
    @JsonIgnore
    private Socio socio;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<RetiroVehiculo> retiros;
}
