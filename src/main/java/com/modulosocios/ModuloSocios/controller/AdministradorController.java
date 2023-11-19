package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.AdministradorDto;
import com.modulosocios.ModuloSocios.model.Administrador;
import com.modulosocios.ModuloSocios.services.AdministradorService;
import java.util.List;

import com.modulosocios.ModuloSocios.services.SocioService;
import com.modulosocios.ModuloSocios.services.VehiculoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    private final AdministradorService administradorServices;
    private final SocioService sociosServices;
    private final VehiculoService vehiculoServices;

    public AdministradorController(AdministradorService administradorServices, VehiculoService vehiculoServices,
            SocioService sociosServices) {
        this.administradorServices = administradorServices;
        this.sociosServices = sociosServices;
        this.vehiculoServices = vehiculoServices;
    }

    @GetMapping("/find-by-name/{nombreAdmin}")
    public ResponseEntity<List<Administrador>> findByName(@PathVariable String nombreAdmin) {
        var administrador = administradorServices.findByname(nombreAdmin);
        return ResponseEntity.ok(administrador);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Administrador>> findAll() {
        var administradorList = administradorServices.findAll();
        return ResponseEntity.ok(administradorList);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<AdministradorDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(administradorServices.findById(id));
    }

    // Cambia el estado del socio a ACEPTADO
    // Si inicialmente el socio estaba en estado de VERIFICACION o RETIRADO
    // (es diferente a la suspensión, ya que un socio puede tener varias
    // suspensiones)
    @PutMapping("/change-socio-status/{idSocio}")
    public ResponseEntity changeSocioStatus(@PathVariable Integer idSocio, @RequestParam String status) {
        return ResponseEntity.ok(sociosServices.changeSocioStatus(idSocio, status));
    }

    @PutMapping("/change-vehicle-status/{idVehiculo}") // Cambia el estado del vehiculo a verificado
    public ResponseEntity changeVehicleStatus(@PathVariable Integer idVehiculo, @RequestParam String status) {
        return ResponseEntity.ok(vehiculoServices.changeVehicleStatus(idVehiculo, status));
    }

    @DeleteMapping("remove-socio/{idSocio}")
    public ResponseEntity<Object> removeSocioFromSystem(@PathVariable Integer idSocio) {
        try {
            this.sociosServices.removeSocioFromSystem(idSocio);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

}
