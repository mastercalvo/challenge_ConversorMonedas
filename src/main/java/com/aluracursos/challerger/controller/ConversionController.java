package com.aluracursos.challerger.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/conversion")
public class ConversionController {
    @GetMapping
    public ResponseEntity<String> convertirMonedas(@RequestParam String from, @RequestParam String to, @RequestParam Double amount) {
        // Aquí deberías colocar la lógica real de conversión
         double conversionRate = obtenerTasaDeConversion(from, to);// Supón que tienes un método para obtener la tasa de conversión
         double convertedAmount = amount * conversionRate;
         return ResponseEntity.ok("El monto convertido es: " + convertedAmount);
         }
         private double obtenerTasaDeConversion(String from, String to) {
        // Implementar la lógica para obtener la tasa de conversión, puede ser una API externa o una base de datos
         return 1.2; // Ejemplo ficticio
              }
}