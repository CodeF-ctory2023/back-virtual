package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.VehiculoDto;
import com.modulosocios.ModuloSocios.mapper.VehiculoMapper;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.services.VehiculoServices;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    private VehiculoServices vehiculosServices;
    private final VehiculoMapper vehiculoMapper;

    // Inyectar Dependencia, para consumir FindByNAME

    public VehiculoController(VehiculoServices vehiculosServices, VehiculoMapper vehiculoMapper) {
        this.vehiculosServices = vehiculosServices;
        this.vehiculoMapper = vehiculoMapper;
    }

    @GetMapping("/find-by-name/{matricula}")
    public ResponseEntity<List<Vehiculo>> buscarPorMatricula(@PathVariable String matricula) {

        var vehiculos = vehiculosServices.findByname(matricula);

        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Vehiculo>> findAll() {
        var vehiculosList = vehiculosServices.findAll();
        return ResponseEntity.ok(vehiculosList);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<VehiculoDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(vehiculosServices.findById(id));
    }

    @PostMapping("/create/{socioId}")
    public ResponseEntity createVehiculo(@RequestBody Vehiculo vehiculo, @PathVariable Integer socioId) {
        return ResponseEntity.ok(vehiculosServices.createVehiculo(vehiculo, socioId));
    }

    @DeleteMapping("/delete/{socioid}")
    public ResponseEntity<String> deleteVehiculo(@PathVariable Integer vehiculoId) {
        vehiculosServices.deleteVehiculo(vehiculoId);
        return ResponseEntity.noContent().build();
    }

}
