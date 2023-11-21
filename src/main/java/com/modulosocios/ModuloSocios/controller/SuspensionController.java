package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.model.Suspension;
import com.modulosocios.ModuloSocios.services.SuspensionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Suspension Controller", description = "Endpoints used for performing suspension operations")
@RequestMapping("/suspension")
public class SuspensionController {

    private SuspensionService suspensionServices;

    public SuspensionController(SuspensionService suspensionServices) {
        this.suspensionServices = suspensionServices;
    }

    @Operation(
            summary = "Find suspension by socio",
            description = "Get suspensions by socio id",
            tags = { "Suspension Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-by-socio/{socioid}")
    public ResponseEntity<List<Suspension>> findBySocioId(@PathVariable Integer socioid) {
        var suspension = suspensionServices.findBySocioId(socioid);
            return ResponseEntity.ok(suspension);
    }

    @Operation(
            summary = "Create suspension for socio",
            description = "Create suspension for socio, given its id and suspension object",
            tags = { "Suspension Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/create/{idSocio}")
    public ResponseEntity<Object> suspenderSocio(@RequestBody Suspension suspension, @PathVariable Integer idSocio) {
        try {
            var response = this.suspensionServices.suspenderSocio(suspension, idSocio);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error suspendiendo usuario");
        }
    }

    @Operation(
            summary = "Create suspension for socio",
            description = "Lift suspension for socio, given its suspension id",
            tags = { "Suspension Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/lift/{idSuspension}")
    public ResponseEntity<Object> levantarSuspension(@PathVariable Integer idSuspension) {
        try {
            var response = this.suspensionServices.levantarSuspension(idSuspension);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error levantando suspension usuario");
        }
    }
}
