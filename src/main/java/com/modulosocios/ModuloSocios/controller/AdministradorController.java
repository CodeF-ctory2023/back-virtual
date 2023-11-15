package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.AdministradorDto;
import com.modulosocios.ModuloSocios.dtos.SocioDto;
import com.modulosocios.ModuloSocios.model.Administrador;
import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.services.AdministradorServices;
import java.util.List;

import com.modulosocios.ModuloSocios.services.SocioServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author anima
 */
@RestController
@RequestMapping("/administrador")
public class AdministradorController {
    
       
    private AdministradorServices administradorServices;

    private final SocioServices sociosServices;
    
    //Inyectar Dependencia, para consumir FindByNAME

    public AdministradorController(AdministradorServices administradorServices,
                                   SocioServices sociosServices) {
        this.administradorServices = administradorServices;
        this.sociosServices = sociosServices;
    }
    
    @GetMapping("/find-by-name/{nombreAdmin}")
    public ResponseEntity<List<Administrador>> findByName (@PathVariable String nombreAdmin){
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

    @PutMapping("/change-socio-status")
    public ResponseEntity changeSocioStatus(@RequestParam Integer id, @RequestParam String status) {
        return ResponseEntity.ok(sociosServices.changeStatusForSocio(id, status));
    }
}
