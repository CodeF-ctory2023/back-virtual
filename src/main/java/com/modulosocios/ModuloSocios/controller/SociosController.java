package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.SociosDto;
import com.modulosocios.ModuloSocios.mapper.SocioMapper;
import com.modulosocios.ModuloSocios.model.Socios;
import com.modulosocios.ModuloSocios.services.AdministradorServices;
import com.modulosocios.ModuloSocios.services.SociosServices;
import java.util.List;
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
    
    //Inyectar Dependencia, para consumir FindByNAME
    private final SocioMapper socioMapper;
    public SociosController(SociosServices sociosServices, SocioMapper socioMapper) {
        this.sociosServices = sociosServices;
        this.socioMapper = socioMapper;
    }
    
    @GetMapping("/find-by-name/{nombre}")
    public ResponseEntity<List<Socios>> findByName (@PathVariable String nombre){
        var socios = sociosServices.findByName(nombre);
        
        return ResponseEntity.ok(socios);
    }
    
    @GetMapping("/find-all")
    public ResponseEntity<List<Socios>> findAll() {
        var sociosList = sociosServices.findAll();
        return ResponseEntity.ok(sociosList);
    }
    
    @GetMapping("/find-id/{id}")
    public ResponseEntity<SociosDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(sociosServices.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity createSocios(@RequestBody SociosDto crearSocios){
        var sociosCrear = socioMapper.toEntity(crearSocios);
        return ResponseEntity.ok(sociosServices.createSocios(sociosCrear,crearSocios.getAdministradorId()));
    }
}
