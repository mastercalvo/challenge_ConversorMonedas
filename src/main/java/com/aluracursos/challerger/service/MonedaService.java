package com.aluracursos.challerger.service;

import com.aluracursos.challerger.model.Moneda;
import com.aluracursos.challerger.repository.MonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonedaService {

    private final MonedaRepository monedaRepository;

    @Autowired
    public MonedaService(MonedaRepository monedaRepository) {
        this.monedaRepository = monedaRepository;
    }

    public List<Moneda> obtenerTodasLasMonedas() {
        return monedaRepository.findAll();
    }

    public Moneda agregarMoneda(Moneda moneda) {
        return monedaRepository.save(moneda);
    }

    public Moneda actualizarMoneda(Long id, Moneda moneda) {
        Moneda monedaExistente = monedaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Moneda no encontrada con el ID: " + id));
        monedaExistente.setCodigo(moneda.getCodigo());
        monedaExistente.setNombre(moneda.getNombre());
        return monedaRepository.save(monedaExistente);
    }

    public void eliminarMoneda(Long id) {
        monedaRepository.deleteById(id);
    }
}
