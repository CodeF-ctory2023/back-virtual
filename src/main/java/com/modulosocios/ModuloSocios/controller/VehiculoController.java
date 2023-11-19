package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.VehiculoDto;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.services.VehiculoService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    private VehiculoService vehiculosServices;

    public VehiculoController(VehiculoService vehiculosServices) {
        this.vehiculosServices = vehiculosServices;
    }

    @GetMapping("/find-by-matricula/{matricula}")
    public ResponseEntity<Object> findByNMatricula(@PathVariable String matricula) {
        var vehiculo = vehiculosServices.findByNMatricula(matricula);
        return ResponseEntity.ok(vehiculo);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Vehiculo>> findAll() {
        var vehiculosList = vehiculosServices.findAll();
        return ResponseEntity.ok(vehiculosList);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<VehiculoDto> findById(@PathVariable Integer id) {
        var vehiculo = vehiculosServices.findById(id);
        return ResponseEntity.ok(vehiculo);
    }

    @PostMapping("/create/{socioId}")
    public ResponseEntity<Object> createVehiculo(@RequestBody Vehiculo newVehiculo, @PathVariable Integer socioId) {
        var vehiculo = vehiculosServices.createVehiculo(newVehiculo, socioId);
        return ResponseEntity.ok(vehiculo);
    }

    @DeleteMapping("/delete/{socioid}")
    public ResponseEntity<Object> deleteVehiculo(@PathVariable Integer vehiculoId) {
        var response = vehiculosServices.deleteVehiculo(vehiculoId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
