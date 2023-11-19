package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.Suspension;
import com.modulosocios.ModuloSocios.services.SuspensionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suspension")
public class SuspensionController {

    private SuspensionService suspensionServices;

    public SuspensionController(SuspensionService suspensionServices) {
        this.suspensionServices = suspensionServices;
    }

    @GetMapping("/find-by-socio/{socioid}")
    public ResponseEntity<Object> findBySocioId(@PathVariable Integer socioid) {
        var suspension = suspensionServices.findBySocioId(socioid);
            return ResponseEntity.ok(suspension);
    }

    @PostMapping("/create/{idSocio}")
    public ResponseEntity<Object> suspenderSocio(@RequestBody Suspension suspension, @PathVariable Integer idSocio) {
        try {
            var response = this.suspensionServices.suspenderSocio(suspension, idSocio);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

    @PutMapping("/lift/{idSuspension}")
    public ResponseEntity<Object> levantarSuspension(@PathVariable Integer idSuspension) {
        try {
            var response = this.suspensionServices.levantarSuspension(idSuspension);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error levantando suspension usuario");
        }
    }
}
