package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.model.Suspension;
import com.modulosocios.ModuloSocios.repository.SuspensionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author anima
 */
@Service

public class SuspensionServices {
    
    private SuspensionRepository suspensionRepository;
    
    //Inyeccion Dependencias por Constructor
    public SuspensionServices(SuspensionRepository suspensionRepository){
        this.suspensionRepository = suspensionRepository;
    }
    
    
    //lista de socios
    public List<Suspension> findByname(Integer socioid){
        var suspension = suspensionRepository.findBySocioId(socioid);
        
        return suspension;
    }
}
