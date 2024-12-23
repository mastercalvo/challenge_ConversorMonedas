package com.aluracursos.challerger.controller;

import com.aluracursos.challerger.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/convert")
    public double convert(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        double rate = conversionService.obtenerTasaDeConversion(from, to);
        return rate * amount;
    }
}
