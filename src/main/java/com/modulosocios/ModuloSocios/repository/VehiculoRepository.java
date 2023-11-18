package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    List<Vehiculo> findByMatriculaStartingWith(String matricula);

}
