package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.VehiculoDto;
import com.modulosocios.ModuloSocios.model.Vehiculo;
import com.modulosocios.ModuloSocios.services.VehiculoService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Vehiculo Controller", description = "Endpoints used for performing vehicle operations")
@RequestMapping("/vehiculo")
public class VehiculoController {

    private VehiculoService vehiculosServices;

    public VehiculoController(VehiculoService vehiculosServices) {
        this.vehiculosServices = vehiculosServices;
    }

    @Operation(
            summary = "Find vehicle by matricula",
            description = "Get vehiculo by matricula",
            tags = { "Vehiculo Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-by-matricula/{matricula}")
    public ResponseEntity<Object> findByNMatricula(@PathVariable String matricula) {
        var vehiculo = vehiculosServices.findByNMatricula(matricula);
        return ResponseEntity.ok(vehiculo);
    }

    @Operation(
            summary = "Find all vehicles",
            description = "Get a list of all vehicles in the system",
            tags = { "Vehiculo Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-all")
    public ResponseEntity<List<Vehiculo>> findAll() {
        var vehiculosList = vehiculosServices.findAll();
        return ResponseEntity.ok(vehiculosList);
    }

    @Operation(
            summary = "Find vehicle by id",
            description = "Get vehiculo by id",
            tags = { "Vehiculo Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-id/{id}")
    public ResponseEntity<VehiculoDto> findById(@PathVariable Integer id) {
        var vehiculo = vehiculosServices.findById(id);
        return ResponseEntity.ok(vehiculo);
    }

    @Operation(
            summary = "Create vehicle for socio",
            description = "Post for creating a vehicle given its socid id",
            tags = { "Vehiculo Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/create/{socioId}")
    public ResponseEntity<Object> createVehiculo(@RequestBody Vehiculo newVehiculo, @PathVariable Integer socioId) {
        var vehiculo = vehiculosServices.createVehiculo(newVehiculo, socioId);
        return ResponseEntity.ok(vehiculo);
    }

    @Operation(
            summary = "Delete vehicle for socio",
            description = "Delete for removing a vehicle given its socid id",
            tags = { "Vehiculo Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/delete/{socioid}")
    public ResponseEntity<Object> deleteVehiculo(@PathVariable Integer vehiculoId) {
        var response = vehiculosServices.deleteVehiculo(vehiculoId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
