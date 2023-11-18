package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.enums.EstadoVerificacionEnum;
import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.model.Suspension;
import com.modulosocios.ModuloSocios.repository.SocioRepository;
import com.modulosocios.ModuloSocios.repository.SuspensionRepository;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SuspensionServices {

    private final SuspensionRepository suspensionRepository;

    private final SocioRepository sociosRepository;

    public SuspensionServices(SuspensionRepository suspensionRepository,
            SocioRepository sociosRepository) {
        this.suspensionRepository = suspensionRepository;
        this.sociosRepository = sociosRepository;
    }

    public List<Suspension> findBySocioId(Integer socioid) {
        var suspension = suspensionRepository.findBySocioId(socioid);

        return suspension;
    }

    public Suspension suspenderSocio(Suspension suspension, Integer idSocio) throws IllegalAccessException {
        Socio socio = getSocio(idSocio);

        suspension.setSocio(socio);
        suspension.setSocioId(socio.getId());
        suspension.setFechaHoraSuspension(new Date());

        // Estableciendo socio en estado de suspension
        socio.setEstadoVerificacion(EstadoVerificacionEnum.SUSPENDIDO.getEstado());
        socio.setFechaSuspension(new Date());
        socio.setMotivoSuspension(suspension.getMotivo());

        sociosRepository.save(socio);

        return suspensionRepository.save(suspension);

    }

    public ResponseEntity<Object> levantarSuspension(Integer idSuspension) throws IllegalAccessException {

        Suspension suspension = getSuspension(idSuspension);
        Socio socio = getSocio(suspension.getSocioId());

        suspensionRepository.delete(suspension);

        List<Suspension> suspensiones = findBySocioId(socio.getId());
        String response = "Suspension eliminada con exito";

        if (suspensiones.isEmpty()) {

            socio.setEstadoVerificacion(EstadoVerificacionEnum.ACEPTADO.getEstado());
            socio.setFechaSuspension(null);
            socio.setMotivoSuspension("");

            this.sociosRepository.save(socio);

            return ResponseEntity.ok(response + " Socio Restaurado con éxito.");

        } else {
            return ResponseEntity.ok(response
                    + "Pero Error, el socio tiene varias suspensiones acumuladas, hasta no eliminar la ultima, segurá en estado de suspension.");
        }

    }

    public ResponseEntity<Object> retirarSocio(Integer idSocio) throws IllegalAccessException {
        Socio socio = getSocio(idSocio);
        socio.setEstadoVerificacion(EstadoVerificacionEnum.RETIRADO.getEstado());
        socio.setFechaSuspension(new Date());
        this.sociosRepository.save(socio);

        return ResponseEntity.ok("Socio ha pasado a estado de RETIRADO.");

    }

    private Socio getSocio(Integer id) throws IllegalAccessException {
        var socioOpt = this.sociosRepository.findById(id);
        if (socioOpt.isEmpty()) {
            throw new IllegalAccessException("Socio consultado no existe");
        }
        var socio = socioOpt.get();
        return socio;
    }

    private Suspension getSuspension(Integer suspensionId) throws IllegalAccessException {
        var suspensionOpt = this.suspensionRepository.findById(suspensionId);
        if (suspensionOpt.isEmpty()) {
            throw new IllegalAccessException("Suspension consultada no existe");
        }
        var suspension = suspensionOpt.get();
        return suspension;
    }
}
