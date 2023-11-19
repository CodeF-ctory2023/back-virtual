package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.dtos.VehiculoDto;
import com.modulosocios.ModuloSocios.enums.EstadoVerificacionEnum;
import com.modulosocios.ModuloSocios.mapper.VehiculoMapper;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.repository.SocioRepository;
import com.modulosocios.ModuloSocios.repository.VehiculoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculosRepository;
    private final VehiculoMapper vehiculoMapper;
    private final SocioRepository sociosRepository;

    private final Logger log = LoggerFactory.getLogger(SocioService.class);

    public VehiculoService(VehiculoRepository vehiculosRepository, VehiculoMapper vehiculoMapper,
            SocioRepository sociosRepository) {
        this.vehiculosRepository = vehiculosRepository;
        this.vehiculoMapper = vehiculoMapper;
        this.sociosRepository = sociosRepository;
    }

    public Vehiculo createVehiculo(Vehiculo vehiculo, Integer socioId) {
        Optional<Socio> socioOptional = sociosRepository.findById(socioId);

        if (socioOptional.isPresent()) {
            Socio socio = socioOptional.get();

            if (Objects.equals(socio.getVehiculo(), null)) {

                if (socio.getEstadoVerificacion().equals("ACEPTADO")) {

                    log.info("Aún no hay un vehiculo asociado a socio");
                    Optional<Vehiculo> vehiculoOptional = vehiculosRepository
                            .findByMatriculaNumber(vehiculo.getMatriculaNumber());

                    if (!vehiculoOptional.isPresent()) {
                        log.info("El vehiculo a insertar es nuevo");
                        vehiculo.setEstadoVerificacion(EstadoVerificacionEnum.PENDIENTE.getEstado());
                        vehiculo.setSocioid(socioId);
                        vehiculo.setSocio(socio);

                        return vehiculosRepository.save(vehiculo);
                    } else {
                        log.info("El vehiculo a insertar ya existía antes");
                        Vehiculo vehiculoExistente = vehiculoOptional.get();
                        vehiculoExistente.setEstadoVerificacion(EstadoVerificacionEnum.PENDIENTE.getEstado());
                        vehiculoExistente.setSocioid(socioId);
                        vehiculoExistente.setSocio(socio);

                        return vehiculosRepository.save(vehiculoExistente);
                    }

                } else {
                    throw new RuntimeException("Error, socio tiene un estado inválido.");
                }
            } else {
                throw new RuntimeException("El socio ya tiene un vehículo asociado.");
            }
        } else {
            throw new RuntimeException("Socio no encontrado con ID: " + socioId);
        }
    }

    public Optional<Vehiculo> findByNMatricula(String matricula) {
        if (Objects.isNull(matricula)) {
            throw new RuntimeException("Se necesita una matricula");
        }
        Optional<Vehiculo> vehiculo = vehiculosRepository.findByMatriculaNumber(matricula);
        if (vehiculo.isPresent()) {
            return vehiculo;
        } else {
            throw new RuntimeException("Vehiculo no encontrado");
        }  
    }

    public List<Vehiculo> findAll() {
        var vehiculosList = vehiculosRepository.findAll();
        return vehiculosList;
    }

    public VehiculoDto findById(Integer id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("ex.vehiculo.object_not_found");
        }
        Optional<Vehiculo> vehiculoOptional = this.vehiculosRepository.findById(id);
        if (vehiculoOptional.isPresent()) {
            return this.vehiculoMapper.toDto(vehiculoOptional.get());
        } else {
            throw new RuntimeException("ex.vehiculo.data_not_found");
        }
    }

    public ResponseEntity<String> deleteVehiculo(Integer vehiculoId) {
        Optional<Vehiculo> optionalVehiculo = vehiculosRepository.findById(vehiculoId);
        if (!optionalVehiculo.isPresent()) {
            throw new NoSuchElementException("El vehiculo no existe.");
        }
        vehiculosRepository.delete(optionalVehiculo.get());
        return ResponseEntity.ok("Vehículo eliminado con éxito.");
    }

    public Boolean changeVehicleStatus(Integer id, String status) {
        Optional<Vehiculo> vehiculoOptional = vehiculosRepository.findById(id);
        if (vehiculoOptional.isEmpty()) {
            throw new RuntimeException("Vehiculo no encontrado");
        }
        Vehiculo vehiculo = vehiculoOptional.get();
        vehiculo.setEstadoVerificacion(EstadoVerificacionEnum.valueOf(status).name());

        vehiculosRepository.save(vehiculo);

        return Boolean.TRUE;
    }

}
