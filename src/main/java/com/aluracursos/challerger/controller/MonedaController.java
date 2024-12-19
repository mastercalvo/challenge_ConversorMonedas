package com.aluracursos.challerger.controller;

import com.aluracursos.challerger.model.Moneda;
import com.aluracursos.challerger.repository.MonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/monedas")
public class MonedaController {
    @Autowired
    private MonedaRepository monedaRepository;
    @GetMapping
    public List<Moneda> obtenerMonedas() {
        return monedaRepository.findAll();
    }
    @PostMapping
    public Moneda crearMonedas(@RequestBody Moneda moneda) {
        return monedaRepository.save(moneda);
    }
}
