package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.Socios;
import com.modulosocios.ModuloSocios.services.SociosServices;
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
@RequestMapping("/socios")
public class SociosController {
    
    private SociosServices sociosServices;
    
    //Inyectar Dependencia, para consumir FindByNAME

    public SociosController(SociosServices sociosServices) {
        this.sociosServices = sociosServices;
    }
    
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Socios>> findByName (@PathVariable String name){
        var socios = sociosServices.findByname(name);
        
        return ResponseEntity.ok(socios);
    }
    
    @GetMapping("/find-all")
    public ResponseEntity<List<Socios>> findAll() {
        var sociosList = sociosServices.findAll();
        return ResponseEntity.ok(sociosList);
    }
    
    
}
