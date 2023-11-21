package com.modulosocios.ModuloSocios.controller;

import com.modulosocios.ModuloSocios.dtos.SocioSimpleDto;

import com.modulosocios.ModuloSocios.model.Socio;

import com.modulosocios.ModuloSocios.services.SocioService;
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
@Tag(name = "Socio Controller", description = "Endpoints used for performing socio operations")
@RequestMapping("/socio")
public class SocioController {

    private SocioService sociosServices;

    public SocioController(SocioService sociosServices) {
        this.sociosServices = sociosServices;
    }

    @Operation(
            summary = "Find socio by name",
            description = "Get list of socios given a string prefix",
            tags = { "Socio Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-by-name/{nombre}")
    public ResponseEntity<List<SocioSimpleDto>> findByName(@PathVariable String nombre) {
        var socios = sociosServices.findByName(nombre);
        return ResponseEntity.ok(socios);
    }

    @Operation(
            summary = "Find all socios",
            description = "Get list of all socios in the system",
            tags = { "Socio Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-all")
    public ResponseEntity<List<SocioSimpleDto>> findAll() {
        var sociosList = sociosServices.findAll();
        return ResponseEntity.ok(sociosList);
    }

    @Operation(
            summary = "Find socio",
            description = "Get a socios given its id the system",
            tags = { "Socio Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SocioSimpleDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-id/{id}")
    public ResponseEntity<SocioSimpleDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(sociosServices.findById(id));
    }

    @Operation(
            summary = "Find socio by cedula",
            description = "Get a socios given its cedula",
            tags = { "Socio Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SocioSimpleDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-by-cedula/{cedula}")
    public ResponseEntity<SocioSimpleDto> findByCedula(@PathVariable String cedula) {
        return ResponseEntity.ok(sociosServices.findByCedula(cedula));
    }

    @Operation(
            summary = "Create socio",
            description = "Create a socio receiving a socio object",
            tags = { "Socio Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SocioSimpleDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/create")
    public ResponseEntity createSocios(@RequestBody Socio socio) {
        return ResponseEntity.ok(sociosServices.createSocios(socio));
    }

    @Operation(
            summary = "Update socio",
            description = "Update a socio receiving a socio object",
            tags = { "Socio Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Socio.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/update")
    public ResponseEntity updateSocio(@RequestBody Socio socio) {
        return ResponseEntity.ok(sociosServices.updateSocio(socio));
    }

    @Operation(
            summary = "Delete socio",
            description = "Delete a socio receiving its unique id",
            tags = { "Socio Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/delete/{socioid}")
    public ResponseEntity<Object> deleteSocio(@PathVariable Integer socioid) {
        var response = sociosServices.deleteSocio(socioid);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Filter socio by field",
            description = "Get a list of socios given a value",
            tags = { "Socio Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/filter-by-value/{valor}")
    public ResponseEntity<List<Socio>> filterByField(@PathVariable String valor) {
        var sociosList = sociosServices.filterByField(valor);
        return ResponseEntity.ok(sociosList);
    }

    @Operation(
            summary = "Find socios by suspension date",
            description = "Get a list of socios given its suspension day",
            tags = { "Socio Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/find-by-suspension-date/{day}")
    public ResponseEntity<List<Socio>> findBySuspensionDate(@PathVariable Integer day) {
        var sociosList = sociosServices.findBySuspensionDate(day);
        return ResponseEntity.ok(sociosList);
    }
}
