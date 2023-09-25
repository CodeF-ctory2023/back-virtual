package com.modulosocios.ModuloSocios.services;

import com.modulosocios.ModuloSocios.model.Socios;
import com.modulosocios.ModuloSocios.repository.SociosRepository;
import jakarta.transaction.Transactional;
import static java.lang.Math.log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.StrictMath.log;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author anima
 */
@Service
@Transactional
public class SociosServices {

    private SociosRepository sociosRepository;

    private final Logger log = LoggerFactory.getLogger(SociosServices.class);


    //Inyeccion Dependencias por Constructor
    public SociosServices(SociosRepository sociosRepository) {
        this.sociosRepository = sociosRepository;
    }

    public Socios findById(Integer id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("ex.socios.object_not_found");
        }
        return sociosRepository.findById(id).orElseThrow(()-> new RuntimeException("ex.socios.data_not_found"));
    }
    
    

    
    public void deleteSocios(Integer sociosId) {
        if (Objects.nonNull(sociosId)) {
            Optional<Socios> sociosOptional = sociosRepository.findById(sociosId);
            if (!sociosOptional.isPresent()) {
                throw new RuntimeException("ex.student.data_not_found");
            }
        }

        sociosRepository.deleteById(sociosId);
    }

    

    
    public Socios createSocios(Socios socios) {
        if (Objects.nonNull(socios.getId_socio_fk())) {
            Optional<Socios> sociosOptional = sociosRepository.findById(socios.getId_socio_fk());
            if (sociosOptional.isPresent()) {
                log.error("Datos duplicados");
                throw new RuntimeException("Datos duplicados");
            }
        }

        try {
            return sociosRepository.save(socios);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("ex.socios.data_constraint_violation");
        }
    }
     public Socios updateSocios (Socios socios) {
        if (Objects.isNull(socios.getId_socio_fk())){
            throw new RuntimeException("ex.student.object_not_found");
        }

        var sociosTransaction = sociosRepository.findById(socios.getId_socio_fk())
                .orElseThrow(() -> new RuntimeException("ex.socios.data_not_found") );

        sociosTransaction.setDocumentoIdentidad(socios.getDocumentoIdentidad());
        sociosTransaction.setCorreo_electronico(socios.getCorreo_electronico());

        return sociosTransaction;
    }


    //lista de socios
    public List<Socios> findByname(String nombre) {
        var socios = sociosRepository.findByNombreStartingWith(nombre);

        return socios;
    }

    public List<Socios> findAll() {

        var sociosList = sociosRepository.findAll();
        return sociosList;

    }
}
