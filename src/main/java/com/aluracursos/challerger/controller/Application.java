//package com.aluracursos.challerger.controller;
//
//import com.aluracursos.challerger.service.ConversionService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//@SpringBootApplication
//public class Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}
//	@Bean
//	CommandLineRunner run(ConversionService conversionService) { return args -> { // Prueba de conversión de monedas
//		 String from = "USD";
//		 String to = "EUR";
//		 double amount = 100.0;
//
//		 double rate = conversionService.obtenerTasaDeConversion(from, to);
//		 double convertedAmount = amount * rate;
//
//		 System.out.printf("La tasa de conversión de %s a %s es: %f%n", from, to, rate);
//		 System.out.printf("El monto convertido es: %f%n", convertedAmount);
//	};
//	}
//
//}
