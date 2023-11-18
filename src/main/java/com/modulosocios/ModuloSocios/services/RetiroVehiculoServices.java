package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.enums.EstadoVerificacionEnum;
import com.modulosocios.ModuloSocios.model.RetiroVehiculo;
import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.repository.RetiroVehiculoRepository;
import com.modulosocios.ModuloSocios.repository.SocioRepository;
import com.modulosocios.ModuloSocios.repository.VehiculoRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RetiroVehiculoServices {

    private RetiroVehiculoRepository retiroVehiculoRepository;
    private SocioRepository socioRepository;
    private VehiculoRepository vehiculoRepository;
    private VehiculoServices vehiculoService;

    // Inyeccion Dependencias por Constructor
    public RetiroVehiculoServices(RetiroVehiculoRepository retiroVehiculoRepository, SocioRepository socioRepository,
            VehiculoRepository vehiculoRepository,
            VehiculoServices vehiculoService) {
        this.socioRepository = socioRepository;
        this.retiroVehiculoRepository = retiroVehiculoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.vehiculoService = vehiculoService;
    }

    public List<RetiroVehiculo> findRetirovehiculo(Integer vehiculoId) {

        List<RetiroVehiculo> retiroVehiculo = retiroVehiculoRepository.findByVehiculoId(vehiculoId);
        if (!retiroVehiculo.isEmpty()) {

            return retiroVehiculo;

        } else {
            throw new NoSuchElementException("Registro no encontrado.");
        }

    }

    public RetiroVehiculo createRetirovehiculo(RetiroVehiculo retiroVehiculo, Integer socioId, Integer vehiculoId) {
        Optional<Socio> optionalSocio = socioRepository.findById(socioId);
        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(vehiculoId);

        if (optionalSocio.isPresent() && optionalVehiculo.isPresent()) {

            Socio socio = optionalSocio.get();
            Vehiculo vehiculo = optionalVehiculo.get();

            if (!vehiculo.getEstadoVerificacion().equals("RETIRADO")) {

                retiroVehiculo.setSocio(socio);
                retiroVehiculo.setVehiculo(vehiculo);
                retiroVehiculo.setSocioid(socio.getId());
                retiroVehiculo.setVehiculoid(vehiculo.getId());
                retiroVehiculo.setFechaHoraRetiro(new Date());

                // Estableciendo vehiculo en estado de retirado
                vehiculo.setSocioid(null);
                vehiculo.setEstadoVerificacion(EstadoVerificacionEnum.RETIRADO.getEstado());

                vehiculoRepository.save(vehiculo);

                return retiroVehiculoRepository.save(retiroVehiculo);
            } else {
                throw new NoSuchElementException("Vehiculo ya ha sido retirado.");
            }

        } else {
            throw new NoSuchElementException("Socio o Vehiculo no encontrado.");
        }
    }
}
