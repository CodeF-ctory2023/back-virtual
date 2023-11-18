package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.RetiroVehiculo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetiroVehiculoRepository extends JpaRepository<RetiroVehiculo, Integer> {

       List<RetiroVehiculo> findByVehiculoId(Integer vehiculoId);

}
