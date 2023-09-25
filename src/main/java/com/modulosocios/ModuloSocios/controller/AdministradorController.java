package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.Administrador;
import com.modulosocios.ModuloSocios.services.AdministradorServices;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anima
 */
@RestController
@RequestMapping("/administrador")
public class AdministradorController {
    
       
    private AdministradorServices administradorServices;
    
    //Inyectar Dependencia, para consumir FindByNAME

    public AdministradorController(AdministradorServices administradorServices) {
        this.administradorServices = administradorServices;
    }
    
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Administrador>> findByName (@PathVariable String nombre){
        var administrador = administradorServices.findByname(nombre);
        
        return ResponseEntity.ok(administrador);
    }
}
