package com.modulosocios.ModuloSocios.model;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "retirovehiculo")
@Data
public class RetiroVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "socioid")
    private Integer socioid;

    @Column(name = "vehiculoid")
    private Integer vehiculoid;

    @Column(name = "fechaHoraRetiro")
    private Date fechaHoraRetiro;

    @Column(name = "justificacion")
    private String justificacion;

    @ManyToOne
    @JoinColumn(name = "socioid", insertable = false, updatable = false)
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "vehiculoid", insertable = false, updatable = false)
    private Vehiculo vehiculo;

}
