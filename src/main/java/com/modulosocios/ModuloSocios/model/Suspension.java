
package com.modulosocios.ModuloSocios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

import lombok.*;

/**
 *
 * @author anima
 */
@Entity
@Table (name = "suspension" )
@Getter
@Setter
@NoArgsConstructor
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
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "socioid", insertable = false, updatable = false, nullable = true)
    @JsonIgnore
    private Socios socios;

    public Suspension(Integer socioId, Date fechaHoraSuspension, String motivo, Socios socios) {
        this.socioId = socioId;
        this.fechaHoraSuspension = fechaHoraSuspension;
        this.motivo = motivo;
        this.socios = socios;
    }
}
