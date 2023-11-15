package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.model.Suspension;
import com.modulosocios.ModuloSocios.repository.SocioRepository;
import com.modulosocios.ModuloSocios.repository.SuspensionRepository;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author anima
 */
@Service

public class SuspensionServices {
    
    private SuspensionRepository suspensionRepository;

    private final SocioRepository sociosRepository;
    
    //Inyeccion Dependencias por Constructor
    public SuspensionServices(SuspensionRepository suspensionRepository,
                              SocioRepository sociosRepository){
        this.suspensionRepository = suspensionRepository;
        this.sociosRepository = sociosRepository;
    }
    
    
    //lista de socios
    public List<Suspension> findByname(Integer socioid){
        var suspension = suspensionRepository.findBySocioId(socioid);
        
        return suspension;
    }

    public Suspension suspenderUsuario(Integer socioid, String motivo) throws IllegalAccessException {
        Socio socio = getSocio(socioid);
        var suspension = new Suspension(socio.getId(), new Date(), motivo, socio);
        this.suspensionRepository.save(suspension);
        return suspension;
    }

    public void confirmarSuspension(Integer suspensionId) throws IllegalAccessException {
        Suspension suspension = getSuspension(suspensionId);
        var socio = suspension.getSocios();
        socio.setEstadoVerificacion("Suspendido");
        socio.setFechaSuspension(new Date());
        this.sociosRepository.save(socio);
    }

    public void retirarSocio(Integer id) throws IllegalAccessException {
        Socio socio = getSocio(id);
        socio.setEstadoVerificacion("Retirado");
        socio.setFechaSuspension(new Date());
        this.sociosRepository.save(socio);
    }

    private Socio getSocio(Integer id) throws IllegalAccessException {
        var socioOpt = this.sociosRepository.findById(id);
        if (socioOpt.isEmpty()) {
            throw new IllegalAccessException("Socio consultado no existe");
        }
        var socio = socioOpt.get();
        return socio;
    }

    public void levantarSuspension(Integer suspensionId) throws IllegalAccessException {
        Suspension suspension = getSuspension(suspensionId);
        var socio = suspension.getSocios();
        socio.setEstadoVerificacion("Verificado");
        socio.setFechaSuspension(null);
        socio.setMotivoSuspension("");
        this.sociosRepository.save(socio);
    }

    private Suspension getSuspension(Integer suspensionId) throws IllegalAccessException {
        var suspensionOpt = this.suspensionRepository.findById(suspensionId);
        if (suspensionOpt.isEmpty()) {
            throw new IllegalAccessException("Socio consultado no existe");
        }
        var suspension = suspensionOpt.get();
        return suspension;
    }
}
