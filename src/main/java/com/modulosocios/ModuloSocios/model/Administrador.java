
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author anima
 */
@Entity
@Table (name = "administrador" )
@Data
public class Administrador
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column (name = "id" )
    private Integer id_administrador_fk;
    
    @Column (name = "nombre" )
    private String nombreAdmin;
    
    @Column (name = "correoelectronico" )
    private String correoElectronicoAdmin;
    
    @Column (name = "telefono" )
    private String telefonoAdmin;
    
    @Column (name = "nombreusuario" )
    private String nombreusuarioAdmin;
   
    @Column (name = "contrasena" )
    private String contrasenaAdmin;
    
    @Column (name = "fecharegistro" )
    private Date fechaRegistroAdmin;
    

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_administrador_fk", insertable = false, updatable = false, nullable = true)
    private Socios socio;
}

