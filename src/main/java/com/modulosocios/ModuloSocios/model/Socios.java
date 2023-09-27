
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
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *
 * @author anima
 */
@Entity
@Table (name = "socio" )
@Data
public class Socios {
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
    
    @Column (name = "licenciaconducir" )
    private String licenciaConducir;
    
    @Column (name = "documentoidentidad" )
    private String documentoIdentidad;
    
    @Column (name = "estadoverificacion" )
    private String estadoVerificacion;
    
    @Column (name = "fecharegistro" )
    private Date fechaRegistro;
    
    @Column (name = "fechaverificacion" )
    private Date fechaVerificacion;
    
    @Column (name = "fechasuspension" )
    private Date fechaSuspension;
    
    @Column (name = "motivosuspension" )
    private String motivoSuspension;
    
    @Column(name = "pendientedeverificacion")
    private Boolean pendientedeVerificacion;
    
    @Column (name = "correo_notificacion" )
    private String correoNotificacion;
    
    @Column (name = "administradorid" )
    private Integer administradorId;
    
    @Column (name = "contrasena" )
    private String contrasena;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "administradorid", insertable = false, updatable = false, nullable = true)
    private Administrador administrador;
    
}
