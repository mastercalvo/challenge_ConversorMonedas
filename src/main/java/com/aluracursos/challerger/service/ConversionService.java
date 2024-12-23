package com.aluracursos.challerger.service;

import com.aluracursos.challerger.exception.ErrorHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Logger;

@Service
public class ConversionService {

    private static final Logger logger = Logger.getLogger(ConversionService.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ErrorHandler errorHandler;

    public double obtenerTasaDeConversion(String from, String to) {
        String url = "https://v6.exchangerate-api.com/v6/2f112ebb1abae4ebacf579b3/pair/" + from + "/" + to;
        logger.info("URL de la API: " + url);

        try {
            // Llama a la API y procesa la respuesta
            String response = restTemplate.getForObject(url, String.class);
            logger.info("Respuesta de la API: " + response);

            // Procesar la respuesta JSON para obtener la tasa de conversión
            JsonNode jsonResponse = new ObjectMapper().readTree(response);
            if (jsonResponse.has("conversion_rate")) {
                return jsonResponse.get("conversion_rate").asDouble();
            } else {
                errorHandler.handleApiError("La respuesta de la API no contiene la tasa de conversión esperada.");
            }

        } catch (Exception e) {
            logger.severe("Error al obtener la tasa de conversión: " + e.getMessage());
            errorHandler.handleApiError("Error al obtener la tasa de conversión", e);
        }

        return 0.0;
    }
}
