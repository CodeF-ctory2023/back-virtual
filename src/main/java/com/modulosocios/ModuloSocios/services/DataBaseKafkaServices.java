package com.modulosocios.ModuloSocios.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class DataBaseKafkaServices {
    @Transactional
    public void processKafkaMessage(String message){
        
    }
}
