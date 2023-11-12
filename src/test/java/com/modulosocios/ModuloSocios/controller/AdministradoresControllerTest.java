package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.AdministradorDto;
import com.modulosocios.ModuloSocios.model.Administrador;
import com.modulosocios.ModuloSocios.services.AdministradorServices;
import com.modulosocios.ModuloSocios.services.SociosServices;
import com.modulosocios.ModuloSocios.enums.EstadoVerificacionEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministradoresControllerTest {

    @MockBean
    private AdministradorServices administradorServices;
    private AdministradorController controller;
    // private SociosServices sociosServices;
    private SociosServices mockSociosServices = mock(SociosServices.class);
    
    @Before
    public void setUp() {
        controller = new AdministradorController(administradorServices, mockSociosServices);
    }

    @Test
    public void testFindByName() {
        String nombreAdmin = "Admin1";
        List<Administrador> administradorList = List.of(createAdministrador(1, "Admin1", "admin1@example.com",
                "123456789", "admin1user", "admin1pass", new Date()));

        when(administradorServices.findByname(nombreAdmin)).thenReturn(administradorList);
        ResponseEntity<List<Administrador>> response = controller.findByName(nombreAdmin);
        // La respuesta debe ser OK (código de estado 200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindAll() {
        // objetos Administrador de prueba
        List<Administrador> administradorList = List.of(
                createAdministrador(1, "Admin1", "admin1@example.com", "123456789", "admin1user", "admin1pass",
                        new Date()),
                createAdministrador(2, "Admin2", "admin2@example.com", "987654321", "admin2user", "admin2pass",
                        new Date())

        );
        when(administradorServices.findAll()).thenReturn(administradorList);
        ResponseEntity<List<Administrador>> response = controller.findAll();
        // La respuesta debe ser OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindById() {
        Integer idAdmin = 1;
        Administrador administrador = createAdministrador(1, "Admin1", "admin1@example.com", "123456789", "admin1user",
                "admin1pass", new Date());
        when(administradorServices.findById(idAdmin)).thenReturn(new AdministradorDto(administrador));
        ResponseEntity<AdministradorDto> response = controller.findById(idAdmin);
        // La respuesta debe ser OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testChangeSocioStatus() {
        Integer idSocio = 1;
        EstadoVerificacionEnum nuevoEstado = EstadoVerificacionEnum.SUSPENDIDO;
        // Configurar el comportamiento del mock
        when(mockSociosServices.changeStatusForSocio(idSocio, nuevoEstado.name())).thenReturn(true);
        ResponseEntity response = controller.changeSocioStatus(idSocio, nuevoEstado.name());
        // La respuesta debe ser OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // Objetos Administrador de prueba
    private Administrador createAdministrador(Integer id, String nombre, String correo, String telefono,
            String nombreUsuario, String contrasena, Date fechaRegistro) {
        Administrador administrador = new Administrador();
        administrador.setId_administrador_fk(id);
        administrador.setNombreAdmin(nombre);
        administrador.setCorreoElectronicoAdmin(correo);
        administrador.setTelefonoAdmin(telefono);
        administrador.setNombreusuarioAdmin(nombreUsuario);
        administrador.setContrasenaAdmin(contrasena);
        administrador.setFechaRegistroAdmin(fechaRegistro);

        return administrador;
    }
}
