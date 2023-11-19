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
public class SuspensionService {

    private final SuspensionRepository suspensionRepository;
    private final SocioRepository sociosRepository;

    public SuspensionService(SuspensionRepository suspensionRepository,
            SocioRepository sociosRepository) {
        this.suspensionRepository = suspensionRepository;
        this.sociosRepository = sociosRepository;
    }

    private Socio getSocio(Integer id) throws IllegalAccessException {
        var socioOpt = this.sociosRepository.findById(id);
        if (socioOpt.isEmpty()) {
            throw new RuntimeException("Socio consultado no existe");
        }
        var socio = socioOpt.get();
        return socio;
    }

    private Suspension getSuspension(Integer suspensionId) throws IllegalAccessException {
        var suspensionOpt = this.suspensionRepository.findById(suspensionId);
        if (suspensionOpt.isEmpty()) {
            throw new RuntimeException("Suspension consultada no existe");
        }
        var suspension = suspensionOpt.get();
        return suspension;
    }

    public List<Suspension> findBySocioId(Integer socioid) {
        var suspension = suspensionRepository.findBySocioId(socioid);

        if (!suspension.isEmpty()) {
            return suspension;

        } else {
            throw new RuntimeException("Suspensiones no encontradas para socio con id: " + socioid);
        }
    }

    public Suspension suspenderSocio(Suspension suspension, Integer idSocio) throws IllegalAccessException {
        Socio socio = getSocio(idSocio);

        suspension.setSocio(socio);
        suspension.setSocioId(socio.getId());
        suspension.setFechaHoraSuspension(new Date());
        suspension.setEstado(EstadoVerificacionEnum.SUSPENDIDO.getEstado());

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

        suspension.setEstado(EstadoVerificacionEnum.REVOCADO.getEstado());

        socio.setEstadoVerificacion(EstadoVerificacionEnum.ACEPTADO.getEstado());
        socio.setFechaSuspension(null);
        socio.setMotivoSuspension("NINGUNO");

        this.suspensionRepository.save(suspension);
        this.sociosRepository.save(socio);

        return ResponseEntity.ok("Socio Restaurado con éxito.");

    }

}
