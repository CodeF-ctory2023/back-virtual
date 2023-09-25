
package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.model.Administrador;
import com.modulosocios.ModuloSocios.repository.AdministradorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author anima
 */
@Service
public class AdministradorServices {
    
     private AdministradorRepository administradorRepository;
    
    //Inyeccion Dependencias por Constructor
    public AdministradorServices(AdministradorRepository administradorRepository){
        this.administradorRepository = administradorRepository;
    }
    
    
    //lista de socios
    public List<Administrador> findByname(String nombre){
        var administrador = administradorRepository.findByNombreAdminStartingWith(nombre);
        
        return administrador;
    }
}
