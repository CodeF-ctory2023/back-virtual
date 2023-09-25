/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.Suspension;
import com.modulosocios.ModuloSocios.services.SuspensionServices;
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
        
        return ResponseEntity.ok(suspension);
    }
}
