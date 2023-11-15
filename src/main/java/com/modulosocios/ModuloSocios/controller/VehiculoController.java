package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.VehiculoDto;
import com.modulosocios.ModuloSocios.mapper.VehiculoMapper;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.services.VehiculoServices;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author anima
 */
@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {
    
    private VehiculoServices vehiculosServices;
    private final VehiculoMapper vehiculoMapper;
    
    //Inyectar Dependencia, para consumir FindByNAME

    public VehiculoController(VehiculoServices vehiculosServices, VehiculoMapper vehiculoMapper) {
        this.vehiculosServices = vehiculosServices;
        this.vehiculoMapper = vehiculoMapper;
    }
    
    @GetMapping("/find-by-name/{matricula}")
    public ResponseEntity<List<Vehiculo>> buscarPorMatricula (@PathVariable  String matricula){

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

    @PostMapping("/create")
    public ResponseEntity createVehiculos(@RequestBody VehiculoDto crearVehiculo){
        var vehiculosCrear = vehiculoMapper.toEntity(crearVehiculo);
        return ResponseEntity.ok(vehiculosServices.createVehiculo(vehiculosCrear,crearVehiculo.getId_vehiculo_fk()));
    }

    /*
    
    @DeleteMapping("/delete")
    public ResponseEntity deleteVehiculo(){
        
    }

     */

}
