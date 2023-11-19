package com.modulosocios.ModuloSocios.repository;

import com.modulosocios.ModuloSocios.model.Socio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SocioRepository extends JpaRepository<Socio, Integer> {

        List<Socio> findByNombreContainingIgnoreCase(String nombre);

        List<Socio> findAllByEstadoVerificacion(String estadoVerificacion);

        Optional<Socio> findByCorreoElectronico(String email);

        Optional<Socio> findByDocumentoIdentidad(String documentoIdentidad);

        @Query(value = "SELECT * " +
                        "FROM SOCIO " +
                        "WHERE fechasuspension IS NOT NULL " +
                        "AND TRUNC(fechasuspension) >= TRUNC(SYSDATE) - :day " +
                        "AND TRUNC(fechasuspension) <= TRUNC(SYSDATE)", nativeQuery = true)
        List<Socio> findBySuspensionDate(@Param("day") Integer day);

        @Query(value = "SELECT * FROM socio WHERE " +
                        "LOWER(id) LIKE LOWER('%' || :valor || '%') OR " +
                        "LOWER(nombre) LIKE LOWER('%' || :valor || '%') OR " +
                        "LOWER(correoelectronico) LIKE LOWER('%' || :valor || '%') OR " +
                        "LOWER(telefono) LIKE LOWER('%' || :valor || '%') OR " +
                        "LOWER(licenciaconducir) LIKE LOWER('%' || :valor || '%') OR " +
                        "LOWER(ciudad) LIKE LOWER('%' || :valor || '%') OR " +
                        "LOWER(estadoverificacion) LIKE LOWER('%' || :valor || '%') OR " +
                        "LOWER(documentoidentidad) LIKE LOWER('%' || :valor || '%')", nativeQuery = true)
        List<Socio> filterByField(@Param("valor") String valor);

}
