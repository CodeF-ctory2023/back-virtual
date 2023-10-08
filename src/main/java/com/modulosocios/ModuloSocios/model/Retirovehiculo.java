
package com.modulosocios.ModuloSocios.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author anima
 */
@Entity
@Table (name = "retirovehiculo" )
@Data
public class Retirovehiculo
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id" )
    private Integer id;
    
    @Column (name = "socioid" )
    private Integer socioid;
    
    @Column (name = "vehiculoid" )
    private Integer vehiculoid;
    
    @Column (name = "fechaHoraRetiro" )
    private Date fechaHoraRetiro;
    
    @Column (name = "justificacion" )
    private String justificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", insertable = false, updatable = false, nullable = true)
    private Vehiculo vehiculo;

      
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "socioid", insertable = false, updatable = false, nullable = true)
    private Socios socios;


}
