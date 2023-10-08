
package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.dtos.VehiculoDto;
import com.modulosocios.ModuloSocios.mapper.VehiculoMapper;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.model.Socios;
import com.modulosocios.ModuloSocios.repository.SociosRepository;
import com.modulosocios.ModuloSocios.repository.VehiculosRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author anima
 */
@Service
public class VehiculosServices {
    
     
    private final VehiculosRepository vehiculosRepository;
    private final VehiculoMapper vehiculoMapper;
    private final SociosRepository sociosRepository;




    //Inyeccion Dependencias por Constructor
    public VehiculosServices(VehiculosRepository vehiculosRepository, VehiculoMapper vehiculoMapper, SociosRepository sociosRepository){
        this.vehiculosRepository = vehiculosRepository;
        this.vehiculoMapper = vehiculoMapper;
        this.sociosRepository = sociosRepository;
    }

   /* public Vehiculo createVehiculo(Vehiculo vehiculo, Integer adminId) {
        var socio = sociosRepository.findById(adminId);
        if (socio.isEmpty()) {
            throw new RuntimeException("Error");
        }
        vehiculo.setId_vehiculo_fk(vehiculo.getId_vehiculo_fk());

        try {
            return vehiculosRepository.save(vehiculo);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(e);
        }
    }*/

    //lista de socios
    public List<Vehiculo> findByname(String matricula){
        var vehiculo = vehiculosRepository.findByMatriculaStartingWith(matricula );
        
        return vehiculo;
    }

    public List<Vehiculo> findAll() {

        var vehiculosList = vehiculosRepository.findAll();
        return vehiculosList;

    }

    public VehiculoDto findById(Integer id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("ex.vehiculo.object_not_found");
        }
        Optional<Vehiculo> vehiculoOptional = this.vehiculosRepository.findById(id);
        if(vehiculoOptional.isPresent()){
            return this.vehiculoMapper.toDto(vehiculoOptional.get());
        }else{
            throw new RuntimeException("ex.vehiculo.data_not_found");
        }
    }


}
