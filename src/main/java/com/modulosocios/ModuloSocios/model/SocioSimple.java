package com.modulosocios.ModuloSocios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Clob;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "socio")
@Data
public class SocioSimple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correoelectronico")
    private String correoElectronico;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "documentoidentidad")
    private String documentoIdentidad;

    @Column(name = "estadoverificacion")
    private String estadoVerificacion;

    @Column(name = "licenciaconducir")
    private String licenciaConducir;

    @Column(name = "pasadojudicial")
    private String pasadoJudicial;

    @Column(name = "foto")
    private String foto;

    @OneToOne(mappedBy = "socio", cascade = CascadeType.ALL)
    private Vehiculo vehiculo;
    
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "administradorid", insertable = false, updatable = false, nullable = true)
    @JsonIgnore
    private Administrador administrador;

    @OneToMany(mappedBy = "socio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Suspension> suspensiones;

}


