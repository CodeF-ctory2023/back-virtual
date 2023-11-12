package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Socios;
import com.modulosocios.ModuloSocios.model.SociosNueva;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anima
 */


public interface SociosNuevaRepository extends JpaRepository<SociosNueva, Integer>{
    
    List <SociosNueva> findByNombreContainingIgnoreCase(String nombre);
}
