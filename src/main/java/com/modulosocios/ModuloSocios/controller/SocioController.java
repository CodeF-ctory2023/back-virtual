package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.SocioSimpleDto;

import com.modulosocios.ModuloSocios.model.Socio;

import com.modulosocios.ModuloSocios.services.SocioService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socio")
public class SocioController {

    private SocioService sociosServices;

    public SocioController(SocioService sociosServices) {
        this.sociosServices = sociosServices;
    }

    @GetMapping("/find-by-name/{nombre}")
    public ResponseEntity<List<SocioSimpleDto>> findByName(@PathVariable String nombre) {
        var socios = sociosServices.findByName(nombre);
        return ResponseEntity.ok(socios);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<SocioSimpleDto>> findAll() {
        var sociosList = sociosServices.findAll();
        return ResponseEntity.ok(sociosList);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<SocioSimpleDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(sociosServices.findById(id));
    }

    @GetMapping("/find-by-cedula/{cedula}")
    public ResponseEntity<SocioSimpleDto> findByCedula(@PathVariable String cedula) {
        return ResponseEntity.ok(sociosServices.findByCedula(cedula));
    }

    @PostMapping("/create")
    public ResponseEntity createSocios(@RequestBody Socio socio) {
        return ResponseEntity.ok(sociosServices.createSocios(socio));
    }

    @PutMapping("/update")
    public ResponseEntity updateSocio(@RequestBody Socio socio) {
        return ResponseEntity.ok(sociosServices.updateSocio(socio));
    }

    @DeleteMapping("/delete/{socioid}")
    public ResponseEntity<Object> deleteSocio(@PathVariable Integer socioid) {
        var response = sociosServices.deleteSocio(socioid);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/filter-by-value/{valor}")
    public ResponseEntity<List<Socio>> filterByField(@PathVariable String valor) {
        var sociosList = sociosServices.filterByField(valor);
        return ResponseEntity.ok(sociosList);
    }

    @GetMapping("/find-by-suspension-date/{day}")
    public ResponseEntity<List<Socio>> findBySuspensionDate(@PathVariable Integer day) {
        var sociosList = sociosServices.findBySuspensionDate(day);
        return ResponseEntity.ok(sociosList);
    }
}
