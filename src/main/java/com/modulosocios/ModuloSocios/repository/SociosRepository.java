package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Socios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anima
 */
@Repository
public interface SociosRepository extends JpaRepository<Socios, Integer>{
    
    List <Socios> findByNombreStartingWith(String nombre);
}
