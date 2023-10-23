package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.SociosDto;
import com.modulosocios.ModuloSocios.dtos.SociosNuevaDto;

import com.modulosocios.ModuloSocios.mapper.SocioMapper;
import com.modulosocios.ModuloSocios.model.Socios;
import com.modulosocios.ModuloSocios.model.SociosNueva;
import com.modulosocios.ModuloSocios.services.AdministradorServices;
import com.modulosocios.ModuloSocios.services.SociosServices;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author anima
 */
@RestController
@RequestMapping("/socio")
public class SociosController {

    private SociosServices sociosServices;

    // Inyectar Dependencia, para consumir FindByNAME
    private final SocioMapper socioMapper;

    public SociosController(SociosServices sociosServices, SocioMapper socioMapper) {
        this.sociosServices = sociosServices;
        this.socioMapper = socioMapper;
    }

    @GetMapping("/find-by-name/{nombre}")
    public ResponseEntity<List<Socios>> findByName(@PathVariable String nombre) {
        var socios = sociosServices.findByName(nombre);

        return ResponseEntity.ok(socios);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Socios>> findAll() {
        var sociosList = sociosServices.findAll();
        return ResponseEntity.ok(sociosList);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<SociosNuevaDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(sociosServices.findById(id));
    }

    /* @PostMapping("/create")
    public ResponseEntity createSocios(@RequestBody SociosDto crearSocios) {
        // var sociosCrear = socioMapper.toEntity(crearSocios);
        Socios sociosCrear = socioMapper.toEntity(crearSocios);

        // Asignar valores predeterminados o permitir valores nulos si es necesario
        if (crearSocios.getLicenciaConducir() == null) {
            sociosCrear.setLicenciaConducir("THC145");
        }
        if (crearSocios.getEstadoVerificacion() == null) {
            sociosCrear.setEstadoVerificacion("Pendiente");
        }
        if (crearSocios.getFechaRegistro() == null) {
           Calendar fechaRegistro = Calendar.getInstance();
            fechaRegistro.set(2023, 9, 28);
            sociosCrear.setFechaRegistro(fechaRegistro.getTime());
        }

        if (crearSocios.getFechaVerificacion() == null) {
            Calendar fechaVerificacion = Calendar.getInstance();
            fechaVerificacion.set(2023, 10, 25);
            sociosCrear.setFechaVerificacion(fechaVerificacion.getTime());
        }
        if (crearSocios.getFechaSuspension() == null) {
            Calendar fechaSuspension = Calendar.getInstance();
            fechaSuspension.set(2023, 11, 28);
            sociosCrear.setFechaSuspension(fechaSuspension.getTime());

        }

        if (crearSocios.getMotivoSuspension() == null) {
            sociosCrear.setMotivoSuspension("Falta de archivos");
        }
        if (crearSocios.getPendientedeVerificacion() == null) {
            sociosCrear.setPendientedeVerificacion(false);
        }
        if (crearSocios.getCorreoNotificacion() == null) {
            sociosCrear.setCorreoNotificacion("notificaciones@example.com");
        }

        if (crearSocios.getAdministradorId() == null) {
            sociosCrear.setAdministradorId(10);
        }

        if (crearSocios.getContrasena() == null) {
            sociosCrear.setContrasena("clave1232");
        }

        return ResponseEntity.ok(sociosServices.createSocios(sociosCrear, crearSocios.getAdministradorId()));

    } */

    @PostMapping("/create")
    public ResponseEntity createSocios(@RequestBody SociosNueva crearSocios) {
       // var sociosCrear = socioMapper.toEntity(crearSocios);
      //  SociosNueva sociosNuevaCrear = socioMapper.toEntity(sociosCrear);


        return ResponseEntity.ok(sociosServices.createSocios(crearSocios));

    }
}

