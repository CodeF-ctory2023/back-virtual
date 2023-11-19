package com.modulosocios.ModuloSocios.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import com.modulosocios.ModuloSocios.model.Administrador;
import com.modulosocios.ModuloSocios.repository.AdministradorRepository;

public class AdministradorServicesTest {

    @Mock
    private AdministradorRepository administradorRepository;

    @InjectMocks
    private AdministradorService administradorServices;

    private Administrador administrador;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        administrador = new Administrador();
        administrador.setContrasenaAdmin("22");
        administrador.setCorreoElectronicoAdmin("Admin22@gmail.com");
        administrador.setId_administrador_fk(22);
        administrador.setNombreAdmin("Admin22");
        administrador.setNombreusuarioAdmin("Admin22");
        administrador.setTelefonoAdmin("33333");
    }

    @Test
    void testFindAll() {
        List<Administrador> administradorList = new ArrayList<>();
        administradorList.add(administrador);

        when(administradorRepository.findAll()).thenReturn(administradorList);

        List<Administrador> result = administradorServices.findAll();

        assertNotNull(result);
        assertTrue(result.contains(administrador));
    }
}
