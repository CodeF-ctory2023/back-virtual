package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.Suspension;
import com.modulosocios.ModuloSocios.services.SuspensionServices;

import java.util.List;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suspension")
public class SuspensionController {

    private SuspensionServices suspensionServices;

    // Inyectar Dependencia, para consumir FindByNAME

    public SuspensionController(SuspensionServices suspensionServices) {
        this.suspensionServices = suspensionServices;
    }

    @GetMapping("/find-by-socio/{socioid}")
    public ResponseEntity<Object> findBySocioId(@PathVariable Integer socioid) {
        var suspension = suspensionServices.findBySocioId(socioid);

        if (!suspension.isEmpty()) {

            return ResponseEntity.ok(suspension);
        } else {

            return ResponseEntity.ok("No hay Suspensiones para socio con id: " + socioid);
        }

    }

    @PostMapping("/create/{idSocio}")
    public ResponseEntity<Object> suspenderSocio(@RequestBody Suspension suspension, @PathVariable Integer idSocio) {

        try {
            return ResponseEntity.ok(this.suspensionServices.suspenderSocio(suspension, idSocio));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

    @PutMapping("/lift/{idSuspension}")
    public ResponseEntity<Object> levantarSuspension(@PathVariable Integer idSuspension) {

        try {
            this.suspensionServices.levantarSuspension(idSuspension);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error levantando suspension usuario");
        }
    }

    @DeleteMapping("remove/{idSocio}")
    public ResponseEntity<Object> retirarSocio(@PathVariable Integer idSocio) {

        try {
            this.suspensionServices.retirarSocio(idSocio);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

}
