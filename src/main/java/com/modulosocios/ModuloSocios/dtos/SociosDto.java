package com.modulosocios.ModuloSocios.dtos;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author anima
 */
@Data
public class SociosDto {

    private Integer id;

    private String nombre;

    private String correoelectronico;

    private String telefono;

    private String licenciaConducir;

    private String documentoidentidad;

    private String estadoverificacion;

    private Date fecharegistro;

    private Date fechaverificacion;

    private Date fechasuspension;

    private String motivosuspension;

    private Boolean pendienteverificacion;

    private String correo_notificacion;

    private Integer administradorid;

    private String contrasena;

    public SociosDto() {
    }

    public SociosDto(Integer id, String nombre, String correoelectronico, String telefono, String licenciaConducir, String documentoidentidad, String estadoverificacion, Date fecharegistro, Date fechaverificacion, Date fechasuspension, String motivosuspension, Boolean pendienteverificacion, String correo_notificacion, Integer administradorid, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.correoelectronico = correoelectronico;
        this.telefono = telefono;
        this.licenciaConducir = licenciaConducir;
        this.documentoidentidad = documentoidentidad;
        this.estadoverificacion = estadoverificacion;
        this.fecharegistro = fecharegistro;
        this.fechaverificacion = fechaverificacion;
        this.fechasuspension = fechasuspension;
        this.motivosuspension = motivosuspension;
        this.pendienteverificacion = pendienteverificacion;
        this.correo_notificacion = correo_notificacion;
        this.administradorid = administradorid;
        this.contrasena = contrasena;
    }
    
    
    
    
}
