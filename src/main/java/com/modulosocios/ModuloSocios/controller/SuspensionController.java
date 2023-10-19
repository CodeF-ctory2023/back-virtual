/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.Suspension;
import com.modulosocios.ModuloSocios.requests.SuspenderSocioRequest;
import com.modulosocios.ModuloSocios.services.SuspensionServices;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author anima
 */
@RestController
@RequestMapping("/suspension")
public class SuspensionController {
    
     private SuspensionServices suspensionServices;
    
    //Inyectar Dependencia, para consumir FindByNAME

    public SuspensionController(SuspensionServices suspensionServices) {
        this.suspensionServices = suspensionServices;
    }
    
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Suspension>> findByName (@PathVariable Integer socioid){
        var suspension = suspensionServices.findByname(socioid);
        // suspenderUsuario
        return ResponseEntity.ok(suspension);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> levantarSuspension(@PathVariable Integer id) {
        ResponseEntity<String> FORBIDDEN = verifyIfUserIsAdmin();
        if (FORBIDDEN != null) return FORBIDDEN;
        try {
            this.suspensionServices.levantarSuspension(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> suspenderSocio(@RequestBody SuspenderSocioRequest request){
        ResponseEntity<String> FORBIDDEN = verifyIfUserIsAdmin();
        if (FORBIDDEN != null) return FORBIDDEN;
        try {
            return ResponseEntity.ok(this.suspensionServices.suspenderUsuario(request.getId(), request.getMotivo()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> retirarSocio(@PathVariable Integer id){
        ResponseEntity<String> FORBIDDEN = verifyIfUserIsAdmin();
        if (FORBIDDEN != null) return FORBIDDEN;
        try {
            this.suspensionServices.retirarSocio(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

    @DeleteMapping("/confirm")
    public ResponseEntity<?> confirmarSuspensionSocio(@RequestBody SuspenderSocioRequest request){
        ResponseEntity<String> FORBIDDEN = verifyIfUserIsAdmin();
        if (FORBIDDEN != null) return FORBIDDEN;
        try {
            this.suspensionServices.confirmarSuspension(request.getId());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

    private static ResponseEntity<String> verifyIfUserIsAdmin() {
        var a = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (!a.stream().findFirst().get().getAuthority().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        }
        return null;
    }
}
