/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.Retirovehiculo;
import com.modulosocios.ModuloSocios.services.RetiroVehiculoServices;
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
@RequestMapping("/retirovehiculo")
public class RetiroVehiculoController {
          
    private RetiroVehiculoServices retiroVehiculoServices;
    
    //Inyectar Dependencia, para consumir FindByNAME

    public RetiroVehiculoController(RetiroVehiculoServices retiroVehiculoServices) {
        this.retiroVehiculoServices = retiroVehiculoServices;
    }
    
    @GetMapping("/find-by-name/{nombre}")
    public ResponseEntity<List<Retirovehiculo>> findByName (@PathVariable Integer vehiculoId){
        var retiroVehiculo = retiroVehiculoServices.findByname(vehiculoId);
        
        return ResponseEntity.ok(retiroVehiculo);
    }
}
