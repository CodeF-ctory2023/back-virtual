package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.repository.VehiculosRepository;
import com.modulosocios.ModuloSocios.services.VehiculosServices;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anima
 */
@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {
    
    
     private VehiculosServices vehiculosServices;
    
    //Inyectar Dependencia, para consumir FindByNAME

    public VehiculoController(VehiculosServices vehiculosServices) {
        this.vehiculosServices = vehiculosServices;
    }
    
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Vehiculo>> buscarPorMatricula (@RequestParam  String matricula){
            
        var vehiculos = vehiculosServices.findByname(matricula);

        return ResponseEntity.ok(vehiculos);
    }
}
