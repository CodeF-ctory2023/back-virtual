package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Retirovehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author anima
 */
@Repository
public interface RetiroVehiculoRepository extends JpaRepository<Retirovehiculo, Integer>{
    
       List<Retirovehiculo> findByVehiculoId(Integer vehiculoId);

}
