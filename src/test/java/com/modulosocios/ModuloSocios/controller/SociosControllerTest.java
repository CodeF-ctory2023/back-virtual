package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.SocioDto;
import com.modulosocios.ModuloSocios.dtos.SocioSimpleDto;
import com.modulosocios.ModuloSocios.mapper.SocioMapper;
import com.modulosocios.ModuloSocios.model.Socio;
import com.modulosocios.ModuloSocios.services.SocioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SociosControllerTest {

    @MockBean
    private SocioService sociosServices;
    private SocioController controller;
    private SocioMapper socioMapper = mock(SocioMapper.class);;

    @Before
    public void setUp() {
        controller = new SocioController(sociosServices);
    }

    /*
     * @Test
     * public void testFindByName() {
     * String nombre = "Socio1";
     * List<SocioSimpleDto> sociosList = List.of(
     * createSocios(1, nombre, "socio1@example.com", "123456789", "Licencia1",
     * "ID1",
     * "Verificado", new Date(), null, null, null, true, "correo1@example.com", 1,
     * "contrasena1"));
     * 
     * when(sociosServices.findByName(nombre)).thenReturn(sociosList);
     * ResponseEntity<List<SocioSimpleDto>> response =
     * controller.findByName(nombre);
     * // La respuesta debe ser OK
     * assertEquals(HttpStatus.OK, response.getStatusCode());
     * }
     * 
     * @Test
     * public void testFindById() {
     * Integer id = 1;
     * SociosDto sociosDto = createSociosDto(1, "Socio1", "socio1@example.com",
     * "123456789", "Licencia1", "ID1",
     * "Verificado",
     * new Date(), null, null, null, true, "correo1@example.com", 1, "contrasena1");
     * when(sociosServices.findById(id)).thenReturn(sociosDto);
     * ResponseEntity<SociosDto> response = controller.findById(id);
     * // La respuesta debe ser OK
     * assertEquals(HttpStatus.OK, response.getStatusCode());
     * }
     * 
     * @Test
     * public void testCreateSocios() {
     * SociosDto sociosDto = new SociosDto();
     * sociosDto.setAdministradorId(1); // ID del administrador
     * 
     * //objetos Socio
     * List<Socios> sociosList = List.of(
     * createSocios(1, "Socio1", "socio1@example.com", "123456789", "Licencia1",
     * "ID1", "Verificado",
     * new Date(), null, null, null, true, "correo1@example.com", 1,
     * "contrasena1"));
     * 
     * when(sociosServices.createSocios(any(Socios.class),
     * eq(1))).thenReturn(sociosList.get(0));
     * ResponseEntity response = controller.createSocios(sociosDto);
     * // La respuesta debe ser OK
     * assertEquals(HttpStatus.OK, response.getStatusCode());
     * }
     * 
     * @Test
     * public void testFindBySuspensionDate() {
     * Integer day = 10;
     * List<Socios> sociosList = List.of(
     * createSocios(1, "Socio1", "socio1@example.com", "123456789", "Licencia1",
     * "ID1", "Verificado",
     * new Date(), null, null, null, true, "correo1@example.com", 1,
     * "contrasena1"));
     * when(sociosServices.findBySuspensionDate(day)).thenReturn(sociosList);
     * ResponseEntity<List<Socios>> response = controller.findBySuspensionDate(day);
     * assertEquals(HttpStatus.OK, response.getStatusCode());
     * }
     * 
     * @Test
     * public void testFindAll() {
     * // objetos Socios de prueba
     * List<Socios> sociosList = List.of(
     * createSocios(1, "Socio1", "socio1@example.com", "123456789", "Licencia1",
     * "ID1", "Verificado",
     * new Date(), null, null, null, true, "correo1@example.com", 1, "contrasena1"),
     * createSocios(2, "Socio2", "socio2@example.com", "987654321", "Licencia2",
     * "ID2", "No verificado",
     * new Date(), null, null, null, false, "correo2@example.com", 2,
     * "contrasena2"));
     * 
     * when(sociosServices.findAll()).thenReturn(sociosList);
     * ResponseEntity<List<Socios>> response = controller.findAll();
     * // La respuesta debe ser OK
     * assertEquals(HttpStatus.OK, response.getStatusCode());
     * }
     */
    // Objetos Socios de prueba
    private Socio createSocios(Integer id, String nombre, String correo, String telefono, String licenciaConducir,
            String documentoIdentidad, String estadoVerificacion, Date fechaRegistro, Date fechaVerificacion,
            Date fechaSuspension, String motivoSuspension, Boolean pendienteDeVerificacion, String correoNotificacion,
            Integer administradorId, String contrasena) {
        Socio socios = new Socio();
        socios.setId(id);
        socios.setNombre(nombre);
        socios.setCorreoElectronico(correo);
        socios.setTelefono(telefono);
        socios.setLicenciaConducir(licenciaConducir);
        socios.setDocumentoIdentidad(documentoIdentidad);
        socios.setEstadoVerificacion(estadoVerificacion);
        socios.setFechaRegistro(fechaRegistro);
        socios.setFechaVerificacion(fechaVerificacion);
        socios.setFechaSuspension(fechaSuspension);
        socios.setMotivoSuspension(motivoSuspension);
        socios.setPendientedeVerificacion(pendienteDeVerificacion);
        socios.setCorreoNotificacion(correoNotificacion);
        socios.setAdministradorId(administradorId);
        socios.setContrasena(contrasena);

        return socios;
    }

    private SocioDto createSociosDto(Integer id, String nombre, String correo, String telefono,
            String licenciaConducir,
            String documentoIdentidad, String estadoVerificacion, Date fechaRegistro, Date fechaVerificacion,
            Date fechaSuspension, String motivoSuspension, Boolean pendienteDeVerificacion, String correoNotificacion,
            Integer administradorId, String contrasena) {
        return null;
    }
}
