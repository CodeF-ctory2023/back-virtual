package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.RetiroVehiculo;
import com.modulosocios.ModuloSocios.services.RetiroVehiculoService;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retirovehiculo")
public class RetiroVehiculoController {

    private RetiroVehiculoService retiroVehiculoServices;

    public RetiroVehiculoController(RetiroVehiculoService retiroVehiculoServices) {
        this.retiroVehiculoServices = retiroVehiculoServices;
    }

    @GetMapping("/find-by-vehiculo/{vehiculoId}")
    public ResponseEntity<List<RetiroVehiculo>> findRetirovehiculo(@PathVariable Integer vehiculoId) {
        List<RetiroVehiculo> retiroVehiculo = retiroVehiculoServices.findRetirovehiculo(vehiculoId);
        return ResponseEntity.ok(retiroVehiculo);
    }

    @PostMapping("/create/{socioId}/{vehiculoId}")
    public ResponseEntity createRetirovehiculo(@RequestBody RetiroVehiculo retiroVehiculo,
            @PathVariable Integer socioId, @PathVariable Integer vehiculoId) {
        return ResponseEntity.ok(retiroVehiculoServices.createRetirovehiculo(retiroVehiculo, socioId, vehiculoId));
    }
}
