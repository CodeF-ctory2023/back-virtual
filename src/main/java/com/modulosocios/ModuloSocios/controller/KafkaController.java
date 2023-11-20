package com.modulosocios.ModuloSocios.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modulosocios.ModuloSocios.services.KafkaServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

 
    private KafkaServices kafkaProducerService;
    

    public KafkaController(KafkaServices kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }


    @PostMapping("/publish")
    public void sendMessage(@RequestBody String message) {
        kafkaProducerService.nuevoEndpoint("KafkaPruebasFabrica", "Hola Mundo");
    }
       
    @GetMapping("/nuevo")
    public String nuevoEndpoint() {
        return "¡Hola desde el nuevo endpoint!";
    }
 
}
