package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Socios;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

/**
 *
 * @author anima
 */


public interface SociosRepository extends JpaRepository<Socios, Integer>{
    
    List <Socios> findByNombreContainingIgnoreCase(String nombre);
    List <Socios> findAllByEstadoVerificacion(String estado);

    Socios findByCorreoElectronico(String email);


    @Query(value = "SELECT * " +
               "FROM SOCIO " +
               "WHERE fechasuspension IS NOT NULL " +
               "AND TRUNC(fechasuspension) >= TRUNC(SYSDATE) - :day " +
               "AND TRUNC(fechasuspension) <= TRUNC(SYSDATE)",
       nativeQuery = true)
List<Socios> findBySuspensionDate(@Param("day") Integer day);
}

