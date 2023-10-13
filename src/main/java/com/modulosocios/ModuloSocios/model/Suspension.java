
package com.modulosocios.ModuloSocios.model;

import jakarta.persistence.*;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author anima
 */
@Entity
@Table (name = "suspension" )
@Data
public class Suspension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column (name = "id" )
    private Integer id;
    
    @Column (name = "socioid" )
    private Integer socioId;
    
    @Column (name = "fechahorasuspension" )
    private Date fechaHoraSuspension;
    
    @Column (name = "motivo" )
    private String motivo;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "socioid", insertable = false, updatable = false, nullable = true)
    private Socios socios;
}
