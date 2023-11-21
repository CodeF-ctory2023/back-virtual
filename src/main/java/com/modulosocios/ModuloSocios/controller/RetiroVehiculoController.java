package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.RetiroVehiculo;
import com.modulosocios.ModuloSocios.services.RetiroVehiculoService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Retiro Vehiculo Controller", description = "Endpoints used for performing retiro vehicle operations")
@RequestMapping("/retirovehiculo")
public class RetiroVehiculoController {

    private RetiroVehiculoService retiroVehiculoServices;

    public RetiroVehiculoController(RetiroVehiculoService retiroVehiculoServices) {
        this.retiroVehiculoServices = retiroVehiculoServices;
    }

    @Operation(
            summary = "Find retiro vehiculo",
            description = "Get list of retiro vehiculo retrieved by vehiculo id",
            tags = { "Retiro Vehiculo Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-by-vehiculo/{vehiculoId}")
    public ResponseEntity<List<RetiroVehiculo>> findRetirovehiculo(@PathVariable Integer vehiculoId) {
        List<RetiroVehiculo> retiroVehiculo = retiroVehiculoServices.findRetirovehiculo(vehiculoId);
        return ResponseEntity.ok(retiroVehiculo);
    }

    @Operation(
            summary = "Create retiro vehiculo",
            description = "Post a single retiro vehiculo",
            tags = { "Retiro Vehiculo Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = RetiroVehiculo.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/create/{socioId}/{vehiculoId}")
    public ResponseEntity createRetirovehiculo(@RequestBody RetiroVehiculo retiroVehiculo,
            @PathVariable Integer socioId, @PathVariable Integer vehiculoId) {
        return ResponseEntity.ok(retiroVehiculoServices.createRetirovehiculo(retiroVehiculo, socioId, vehiculoId));
    }
}
