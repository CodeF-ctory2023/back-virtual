package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.dtos.SocioSimpleDto;
import com.modulosocios.ModuloSocios.enums.EstadoVerificacionEnum;
import com.modulosocios.ModuloSocios.mapper.SocioMapper;
import com.modulosocios.ModuloSocios.model.Socio;

import com.modulosocios.ModuloSocios.repository.SocioRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SocioService {

    private final SocioRepository sociosRepository;
    private final SocioMapper socioMapper;
    private final Logger log = LoggerFactory.getLogger(SocioService.class);

    public SocioService(SocioRepository sociosRepository, SocioMapper socioMapper) {
        this.sociosRepository = sociosRepository;
        this.socioMapper = socioMapper;
    }

    public SocioSimpleDto findById(Integer id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Se requiere un ID");
        }
        Optional<Socio> sociosOptional = this.sociosRepository.findById(id);
        if (sociosOptional.isPresent()) {
            return this.socioMapper.toDto(sociosOptional.get());
        } else {
            throw new RuntimeException("Socio No enontrado");
        }
    }

    public SocioSimpleDto findByCedula(String cedula) {
        if (Objects.isNull(cedula)) {
            throw new RuntimeException("Se requiere una cedula");
        }
        Optional<Socio> sociosOptional = this.sociosRepository.findByDocumentoIdentidad(cedula);
        if (sociosOptional.isPresent()) {
            return this.socioMapper.toDto(sociosOptional.get());
        } else {
            throw new RuntimeException("Socio no encontrado");
        }
    }

    public List<SocioSimpleDto> findByName(String nombre) {
        if (Objects.isNull(nombre)) {
            throw new RuntimeException("Se requiere un Nombre");
        }
        List<Socio> sociosList = this.sociosRepository.findByNombreContainingIgnoreCase(nombre);
        if (!sociosList.isEmpty()) {
            List<SocioSimpleDto> sociosDtoList = sociosList.stream()
                    .map(socioMapper::toDto)
                    .collect(Collectors.toList());

            return sociosDtoList;
        } else {
            throw new RuntimeException("Socio no encontrado");
        }
    }

    public List<SocioSimpleDto> findAll() {
        List<Socio> sociosList = sociosRepository.findAll();

        if (!sociosList.isEmpty()) {
            List<SocioSimpleDto> sociosDtoList = sociosList.stream()
                    .map(socioMapper::toDto)
                    .collect(Collectors.toList());

            return sociosDtoList;

        } else {
            throw new RuntimeException("No hay socios");
        }

    }

    public List<Socio> filterByField(String valor) {
        var sociosList = sociosRepository.filterByField(valor);

        if (sociosList.isEmpty()) {
            throw new RuntimeException("No se encuentran socios con valor " + valor);

        }
        return sociosList;

    }

    public SocioSimpleDto createSocios(Socio socio) {
        try {
            socio.setEstadoVerificacion(EstadoVerificacionEnum.PENDIENTE.getEstado());
            socio.setFechaRegistro(new Date());
            sociosRepository.save(socio);

            return this.socioMapper.toDto(socio);

        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error creando socio" + e);
        }
    }

    public Socio updateSocio(Socio socios) {
        try {
            if (Objects.isNull(socios.getId())) {
                throw new RuntimeException("Socio no existe");
            }

            var sociosTransaction = sociosRepository.findById(socios.getId())
                    .orElseThrow(() -> new RuntimeException("ex.socios.data_not_found"));

            if (!sociosTransaction.getEstadoVerificacion().equals("RETIRADO")) {

                sociosTransaction.setNombre(socios.getNombre());
                sociosTransaction.setCorreoElectronico(socios.getCorreoElectronico());
                sociosTransaction.setTelefono(socios.getTelefono());
                sociosTransaction.setCiudad(socios.getCiudad());
                sociosTransaction.setDocumentoIdentidad(socios.getDocumentoIdentidad());
                sociosTransaction.setLicenciaConducir(socios.getLicenciaConducir());
                sociosTransaction.setPasadoJudicial(socios.getPasadoJudicial());
                sociosTransaction.setFoto(socios.getFoto());

                return sociosRepository.save(sociosTransaction);

            } else {
                throw new RuntimeException(
                        "ERROR. Socio está en estado RETIRADO, contacte al ADMINISTRADOR");
            }
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error actualziando socio" + e);
        }
    }

    public Boolean changeSocioStatus(Integer id, String status) {
        var optionalSocio = sociosRepository.findById(id);
        if (optionalSocio.isEmpty()) {
            throw new RuntimeException("Socio no encontrado");
        }
        Socio socio = optionalSocio.get();
        socio.setEstadoVerificacion(EstadoVerificacionEnum.valueOf(status).name());
        socio.setFechaVerificacion(new Date());
        socio.setFechaRetiro(null);
        socio.setPendientedeVerificacion(false);

        sociosRepository.save(socio);
        return Boolean.TRUE;
    }

    public ResponseEntity<Object> deleteSocio(Integer sociosId) {
        if (Objects.isNull(sociosId)) {
            throw new RuntimeException("El ID del socio no puede ser nulo");
        }

        Optional<Socio> sociosOptional = sociosRepository.findById(sociosId);
        if (!sociosOptional.isPresent()) {
            throw new RuntimeException("Socio no encontrado");
        }

        sociosRepository.deleteById(sociosId);
        log.info("SOCIO ELIMINADO");

        return ResponseEntity.ok("Socio eliminado");

    }

    public List<Socio> findBySuspensionDate(Integer day) {
        var sociosList = sociosRepository.findBySuspensionDate(day);
        if (sociosList.isEmpty()) {
            throw new RuntimeException(
                    "No se encuentran socios con fecha de suspensión dentro de " + day + " días vigentes");

        }
        return sociosList;

    }

    public ResponseEntity<Object> removeSocioFromSystem(Integer idSocio) throws IllegalAccessException {
        Optional<Socio> optionalSocio = sociosRepository.findById(idSocio);
        if (!optionalSocio.isPresent()) {
            throw new RuntimeException("Socio no encontrado");
        }
        Socio socio = optionalSocio.get();
        socio.setEstadoVerificacion(EstadoVerificacionEnum.RETIRADO.getEstado());
        socio.setFechaRetiro(new Date());
        this.sociosRepository.save(socio);

        return ResponseEntity.ok("Socio ha pasado a estado RETIRADO.");

    }

    // Verifica los Socios retirados, todos los dias a medianoche
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteNonActiveSociosFromSystem() {
        try {

            log.info("AUTOMATIC REMOVAL SOCIOS ENABLED");
            LocalDate currentDate = LocalDate.now();

            List<Socio> sociosRetirados = sociosRepository.findAllByEstadoVerificacion("RETIRADO");
            List<Socio> sociosAEliminar = sociosRetirados.stream()
                    .filter(socio -> {
                        Date fechaRetiro = socio.getFechaRetiro();
                        LocalDate localDateRetiro = fechaRetiro.toInstant().atZone(ZoneId.systemDefault())
                                .toLocalDate();
                        return ChronoUnit.DAYS.between(localDateRetiro, currentDate) > 30;// En un lapso de 30 días del
                                                                                          // socio estar RETIRADO, se
                                                                                          // elimina del sistema
                    })
                    .collect(Collectors.toList());

            sociosAEliminar.forEach(socio -> {
                sociosRepository.deleteById(socio.getId());
                log.info("Removed socio with ID: {}", socio.getId());
            });

            log.info("Automatic removal completed. {} socios removed.", sociosAEliminar.size());
        } catch (Exception e) {
            throw new NoSuchElementException("ERROR." + e);
        }
    }
}
