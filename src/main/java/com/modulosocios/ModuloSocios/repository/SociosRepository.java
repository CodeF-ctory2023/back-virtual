package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Socios;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anima
 */


public interface SociosRepository extends JpaRepository<Socios, Integer>{
    
    List <Socios> findByNombreContainingIgnoreCase(String nombre);
    List <Socios> findAllByEstadoVerificacion(String estado);

    Socios findByCorreoElectronico(String email);

    // List<Socios> findByStatusAndDeactivationTimeBefore(String status, Date dateTime);


}
