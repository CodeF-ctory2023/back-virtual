
package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.model.Retirovehiculo;
import com.modulosocios.ModuloSocios.repository.RetiroVehiculoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author anima
 */
@Service
public class RetiroVehiculoServices {
    
    private RetiroVehiculoRepository retiroVehiculoRepository;
    
    //Inyeccion Dependencias por Constructor
    public RetiroVehiculoServices(RetiroVehiculoRepository retiroVehiculoRepository){
        this.retiroVehiculoRepository = retiroVehiculoRepository;
    }
    
    
    //lista de socios
    public List<Retirovehiculo> findByname(Integer vehiculoId){
        System.out.println("Llega aca");
        var retiroVehiculo = retiroVehiculoRepository.findByVehiculoId(vehiculoId);
        
        return retiroVehiculo;
    }
}
