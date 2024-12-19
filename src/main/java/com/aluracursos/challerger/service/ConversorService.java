package com.aluracursos.challerger.service;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
@Service
public class ConversorService {
    @Autowired
    private ApiService apiService;

    public double convertirMonedas(String monedaBase, String monedaDestino, double cantidad) {
        try { JsonObject jsonObject = apiService.obtenerTasaCambio(monedaBase);
            double tasaCambio = jsonObject.getAsJsonObject("rates").get(monedaDestino).getAsDouble();
            return cantidad * tasaCambio;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
