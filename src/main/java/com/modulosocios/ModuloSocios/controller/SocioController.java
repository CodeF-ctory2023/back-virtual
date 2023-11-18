package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.SocioDto;
import com.modulosocios.ModuloSocios.dtos.SocioSimpleDto;

import com.modulosocios.ModuloSocios.mapper.SocioMapper;
import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.model.SocioSimple;
import com.modulosocios.ModuloSocios.services.AdministradorServices;
import com.modulosocios.ModuloSocios.services.SocioServices;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socio")
public class SocioController {

    private SocioServices sociosServices;
    private final SocioMapper socioMapper;

    public SocioController(SocioServices sociosServices, SocioMapper socioMapper) {
        this.sociosServices = sociosServices;
        this.socioMapper = socioMapper;
    }

    @GetMapping("/find-by-name/{nombre}")
    public ResponseEntity<List<Socio>> findByName(@PathVariable String nombre) {
        var socios = sociosServices.findByName(nombre);

        return ResponseEntity.ok(socios);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<SocioSimple>> findAll() {
        var sociosList = sociosServices.findAll();
        return ResponseEntity.ok(sociosList);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<SocioSimpleDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(sociosServices.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity createSocios(@RequestBody SocioSimple crearSocios) {
        // var sociosCrear = socioMapper.toEntity(crearSocios);
        // SociosNueva sociosNuevaCrear = socioMapper.toEntity(sociosCrear);

        return ResponseEntity.ok(sociosServices.createSocios(crearSocios));

    }

    @PutMapping("/update")
    public ResponseEntity updateSocio(@RequestBody Socio socio) {
        return ResponseEntity.ok(sociosServices.updateSocio(socio));
    }

    @DeleteMapping("/delete/{socioid}")
    public ResponseEntity<String> deleteSocio(@PathVariable Integer socioid) {
        sociosServices.deleteSocio(socioid);
        return ResponseEntity.noContent().build();
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
