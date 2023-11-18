package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.model.SocioSimple;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SocioSimpleRepository extends JpaRepository<SocioSimple, Integer> {

    List<SocioSimple> findByNombreContainingIgnoreCase(String nombre);
}
