
package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.repository.VehiculosRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author anima
 */
@Service
public class VehiculosServices {
    
     
    private VehiculosRepository vehiculosRepository;
    
    //Inyeccion Dependencias por Constructor
    public VehiculosServices(VehiculosRepository vehiculosRepository){
        this.vehiculosRepository = vehiculosRepository;
    }
    
    
    //lista de socios
    public List<Vehiculo> findByname(String matricula){
        var vehiculo = vehiculosRepository.findByMatriculaStartingWith(matricula );
        
        return vehiculo;
    }
}
