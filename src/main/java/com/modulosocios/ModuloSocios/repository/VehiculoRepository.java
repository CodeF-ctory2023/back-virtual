package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Vehiculo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    Optional<Vehiculo> findById(Integer id);

    List<Vehiculo> findBySocioid(Integer socioid);

    Optional<Vehiculo> findByMatriculaNumber(String matriculaNumber);

}
