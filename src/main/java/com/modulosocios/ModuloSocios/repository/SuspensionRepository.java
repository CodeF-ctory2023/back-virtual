package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.model.Suspension;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspensionRepository extends JpaRepository<Suspension, Integer> {

        List<Suspension> findBySocioId(Integer socioid);

}
