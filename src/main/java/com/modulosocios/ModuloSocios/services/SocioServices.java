package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.dtos.SocioDto;
import com.modulosocios.ModuloSocios.dtos.SocioSimpleDto;
import com.modulosocios.ModuloSocios.enums.EstadoVerificacionEnum;
import com.modulosocios.ModuloSocios.mapper.SocioMapper;
import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.model.SocioSimple;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.repository.AdministradorRepository;
import com.modulosocios.ModuloSocios.repository.SocioSimpleRepository;
import com.modulosocios.ModuloSocios.repository.VehiculoRepository;
import com.modulosocios.ModuloSocios.repository.SocioRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class SocioServices {
    private final SocioRepository sociosRepository;
    private final SocioSimpleRepository sociosNuevaRepository;
    private final SocioMapper socioMapper;

    private final AdministradorRepository administradorRepository;
    private final VehiculoRepository vehiculoRepository;

    private final Logger log = LoggerFactory.getLogger(SocioServices.class);

    // Inyeccion Dependencias por Constructor
    public SocioServices(SocioRepository sociosRepository, SocioMapper socioMapper,
            AdministradorRepository administradorRepository, SocioSimpleRepository sociosNuevaRepository,
            VehiculoRepository vehiculoRepository) {
        this.sociosRepository = sociosRepository;
        this.socioMapper = socioMapper;
        this.administradorRepository = administradorRepository;
        this.sociosNuevaRepository = sociosNuevaRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public SocioSimpleDto findById(Integer id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("ex.socios.object_not_found");
        }
        Optional<SocioSimple> sociosOptional = this.sociosNuevaRepository.findById(id);
        if (sociosOptional.isPresent()) {
            return this.socioMapper.toDto(sociosOptional.get());
        } else {
            throw new RuntimeException("ex.socios.data_not_found");
        }
    }

    public void deleteSocios(Integer sociosId) {
        if (Objects.nonNull(sociosId)) {
            Optional<Socio> sociosOptional = sociosRepository.findById(sociosId);
            if (!sociosOptional.isPresent()) {
                throw new RuntimeException("ex.student.data_not_found");
            }
        }

        sociosRepository.deleteById(sociosId);
    }

    /*
     * public Socios createSocios(Socios socios, Integer adminId) {
     * var admin = administradorRepository.findById(adminId);
     * if (admin.isEmpty()) {
     * throw new RuntimeException("Error");
     * }
     * socios.setAdministrador(admin.get());
     * 
     * try {
     * return sociosRepository.save(socios);
     * } catch (DataIntegrityViolationException e) {
     * throw new RuntimeException(e);
     * }
     * }
     */
    public SocioSimple createSocios(SocioSimple sociosCrear) {

        try {
            sociosCrear.setEstadoVerificacion(EstadoVerificacionEnum.PENDIENTE.getEstado());
            return sociosNuevaRepository.save(sociosCrear);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(e);
        }
    }

    public Socio updateSocio(Socio socios) {
        if (Objects.isNull(socios.getId())) {
            throw new RuntimeException("ex.student.object_not_found");
        }

        var sociosTransaction = sociosRepository.findById(socios.getId())
                .orElseThrow(() -> new RuntimeException("ex.socios.data_not_found"));

        sociosTransaction.setNombre(socios.getNombre());
        sociosTransaction.setCorreoElectronico(socios.getCorreoElectronico());
        sociosTransaction.setTelefono(socios.getTelefono());
        sociosTransaction.setCiudad(socios.getCiudad());
        sociosTransaction.setDocumentoIdentidad(socios.getDocumentoIdentidad());
        sociosTransaction.setLicenciaConducir(socios.getLicenciaConducir());
        sociosTransaction.setPasadoJudicial(socios.getPasadoJudicial());
        sociosTransaction.setFoto(socios.getFoto());

        return sociosRepository.save(sociosTransaction);
    }

    public List<Socio> findByName(String nombre) {
        var socios = sociosRepository.findByNombreContainingIgnoreCase(nombre);

        return socios;
    }

    public List<SocioSimple> findAll() {

        var sociosList = sociosNuevaRepository.findAll();
        return sociosList;

    }

    public Boolean changeStatusForSocio(Integer id, String status) {
        var socio = sociosRepository.findById(id);
        if (socio.isEmpty()) {
            throw new RuntimeException("Socio not found");
        }
        var mySocio = socio.get();
        mySocio.setEstadoVerificacion(EstadoVerificacionEnum.valueOf(status).name());
        mySocio.setFechaVerificacion(new Date());
        mySocio.setPendientedeVerificacion(false);
        
        sociosRepository.save(mySocio);
        return Boolean.TRUE;
    }

    public Boolean changeVehicleStatus(Integer id, String status) {
        var vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isEmpty()) {
            throw new RuntimeException("Vehiculo not found");
        }
        Vehiculo myVehiculo = vehiculo.get();
        myVehiculo.setEstadoVerificacion(EstadoVerificacionEnum.valueOf(status).name());

        vehiculoRepository.save(myVehiculo);

        return Boolean.TRUE;
    }

    public List<Socio> findBySuspensionDate(Integer day) {

        var sociosList = sociosRepository.findBySuspensionDate(day);
        if (sociosList.isEmpty()) {
            log.info("No se encuentran socios con fecha de suspensión dentro de " + day + " días vigentes");

        }
        return sociosList;

    }

    public List<Socio> filterByField(String valor) {

        var sociosList = sociosRepository.filterByField(valor);

        if (sociosList.isEmpty()) {
            log.info("No se encuentran socios con valor " + valor);

        }
        return sociosList;

    }

    public ResponseEntity<String> deleteSocio(Integer socioid) {
        Optional<Socio> optionalSocio = sociosRepository.findById(socioid);
        if (!optionalSocio.isPresent()) {
            throw new NoSuchElementException("El Socio no existe.");
        }
        sociosRepository.delete(optionalSocio.get());
        return ResponseEntity.ok("Socio eliminado con éxito.");
    }

    /**
     * TODO:
     * - Configure cron for running every day at a given time
     * - Modify method for deleting socios when given time has passed
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteNonActiveProducts() {
        log.info("BEGIN DELETION");
        List<Socio> products = sociosRepository.findAllByEstadoVerificacion("Retirado");
        products.forEach(product -> sociosRepository.deleteById(product.getId()));
    }
}
