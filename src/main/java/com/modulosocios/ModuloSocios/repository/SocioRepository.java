package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Socio;

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


public interface SocioRepository extends JpaRepository<Socio, Integer>{
    
    List <Socio> findByNombreContainingIgnoreCase(String nombre);
    List <Socio> findAllByEstadoVerificacion(String estado);

    Socio findByCorreoElectronico(String email);


    @Query(value = "SELECT * " +
               "FROM SOCIO " +
               "WHERE fechasuspension IS NOT NULL " +
               "AND TRUNC(fechasuspension) >= TRUNC(SYSDATE) - :day " +
               "AND TRUNC(fechasuspension) <= TRUNC(SYSDATE)",
       nativeQuery = true)
List<Socio> findBySuspensionDate(@Param("day") Integer day);
}

