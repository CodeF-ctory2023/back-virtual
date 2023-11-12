package com.modulosocios.ModuloSocios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ModuloSociosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuloSociosApplication.class, args);
	}

}
