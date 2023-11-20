package com.modulosocios.ModuloSocios.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class KafkaServices {
    
   @Autowired
    public KafkaTemplate<String,String> kafkaTemplate;

    
    public void nuevoEndpoint(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }



}
