package com.aluracursos.challerger.controller;

import com.aluracursos.challerger.model.Moneda;
import com.aluracursos.challerger.service.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MonedaController {

    private final MonedaService monedaService;

    @Autowired
    public MonedaController(MonedaService monedaService) {
        this.monedaService = monedaService;
    }

    @GetMapping("/monedas")
    public List<Moneda> obtenerTodasLasMonedas() {
        return monedaService.obtenerTodasLasMonedas();
    }

    @PostMapping("/monedas")
    public Moneda agregarMoneda(@RequestBody Moneda moneda) {
        return monedaService.agregarMoneda(moneda);
    }

    @PutMapping("/monedas/{id}")
    public Moneda actualizarMoneda(@PathVariable Long id, @RequestBody Moneda moneda) {
        return monedaService.actualizarMoneda(id, moneda);
    }

    @DeleteMapping("/monedas/{id}")
    public void eliminarMoneda(@PathVariable Long id) {
        monedaService.eliminarMoneda(id);
    }
}
