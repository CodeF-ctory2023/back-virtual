package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.AdministradorDto;
import com.modulosocios.ModuloSocios.enums.EstadoVerificacionEnum;
import com.modulosocios.ModuloSocios.model.Administrador;
import com.modulosocios.ModuloSocios.services.AdministradorService;
import java.util.List;

import com.modulosocios.ModuloSocios.services.SocioService;
import com.modulosocios.ModuloSocios.services.VehiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrador")
@Tag(name = "Administrador", description = "Endpoints used by the administrator only")
public class AdministradorController {

    private final AdministradorService administradorServices;
    private final SocioService sociosServices;
    private final VehiculoService vehiculoServices;

    public AdministradorController(AdministradorService administradorServices, VehiculoService vehiculoServices,
            SocioService sociosServices) {
        this.administradorServices = administradorServices;
        this.sociosServices = sociosServices;
        this.vehiculoServices = vehiculoServices;
    }

    @Operation(
            summary = "Retrieve a list of administrators by its name",
            description = "Get method for retrieving all administrators which share a common string prefix",
            tags = { "Administrador" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-by-name/{nombreAdmin}")
    public ResponseEntity<List<Administrador>> findByName(@PathVariable String nombreAdmin) {
        var administrador = administradorServices.findByname(nombreAdmin);
        return ResponseEntity.ok(administrador);
    }

    @Operation(
            summary = "Retrieve a list of all the administrators in the system",
            description = "Get method for retrieving all administrators in the application database",
            tags = { "Administrador" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-all")
    public ResponseEntity<List<Administrador>> findAll() {
        var administradorList = administradorServices.findAll();
        return ResponseEntity.ok(administradorList);
    }

    @Operation(
            summary = "Retrieve an administrator by id",
            description = "Get method for retrieving a single administrator by id",
            tags = { "Administrador" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AdministradorDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-id/{id}")
    public ResponseEntity<AdministradorDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(administradorServices.findById(id));
    }

    // Cambia el estado del socio a ACEPTADO
    // Si inicialmente el socio estaba en estado de VERIFICACION o RETIRADO
    // (es diferente a la suspensión, ya que un socio puede tener varias
    // suspensiones)
    @Operation(
            summary = "Change socio status",
            description = "Put method for changing a socio status",
            tags = { "Administrador" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/change-socio-status/{idSocio}")
    public ResponseEntity<Boolean> changeSocioStatus(@PathVariable Integer idSocio, @RequestParam EstadoVerificacionEnum status) {
        return ResponseEntity.ok(sociosServices.changeSocioStatus(idSocio, status.getEstado()));
    }

    @Operation(
            summary = "Change vehicle status",
            description = "Put method for changing a vehicle status",
            tags = { "Administrador" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/change-vehicle-status/{idVehiculo}") // Cambia el estado del vehiculo a verificado
    public ResponseEntity<Boolean> changeVehicleStatus(@PathVariable Integer idVehiculo, @RequestParam EstadoVerificacionEnum status) {
        return ResponseEntity.ok(vehiculoServices.changeVehicleStatus(idVehiculo, status.getEstado()));
    }

    @Operation(
            summary = "Remove socio from system",
            description = "Delete method for removing a socio from the system, given its id",
            tags = { "Administrador" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("remove-socio/{idSocio}")
    public ResponseEntity<Object> removeSocioFromSystem(@PathVariable Integer idSocio) {
        try {
            this.sociosServices.removeSocioFromSystem(idSocio);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

}
