
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
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Clob;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anima
 */
@Entity
@Table (name = "socio" )
@Data
public class SociosNueva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column (name = "id" )
    private Integer id;
    
    @Column (name = "nombre" )
    private String nombre;
    
    @Column (name = "correoelectronico" )
    private String correoElectronico;
    
    @Column (name = "telefono" )
    private String telefono;

    @Column (name = "documentoidentidad" )
    private String documentoIdentidad;

    @Column (name = "licenciaconducir" )
    private String licenciaConducir;

    @Column (name = "pasadojudicial" )
    private String pasadoJudicial;

    /* @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;
           */
    
}
