package com.modulosocios.ModuloSocios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modulosocios.ModuloSocios.services.KafkaServices;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Kafka Controller", description = "Endpoints used for connecting with Apache Kafka")
@RequestMapping("/kafka")
public class KafkaController {

 
    private KafkaServices kafkaProducerService;
    

    public KafkaController(KafkaServices kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }


    @Operation(
            summary = "Send message",
            description = "Post a message to Kafka topic",
            tags = { "Kafka Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/publish")
    public void sendMessage(@RequestBody String message) {
        kafkaProducerService.nuevoEndpoint("KafkaPruebasFabrica", "Hola Mundo");
    }

    @Operation(
            summary = "Dummy test endpoint",
            description = "Returns a basic Hello world",
            tags = { "Kafka Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/nuevo")
    public String nuevoEndpoint() {
        return "¡Hola desde el nuevo endpoint!";
    }
 
}
