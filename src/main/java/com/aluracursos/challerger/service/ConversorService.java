package com.aluracursos.challerger.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConversorService {

    @Autowired
    private ApiService apiService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws IOException {
        String url = "https://api.exchangerate-api.com/v4/latest/" + monedaOrigen;
        JsonNode jsonNode = apiService.getJsonData(url);
        JsonNode tasaNode = jsonNode.get("rates").get(monedaDestino);
        return tasaNode.asDouble();
    }
}
