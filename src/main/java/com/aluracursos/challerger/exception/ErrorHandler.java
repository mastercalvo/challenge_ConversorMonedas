package com.aluracursos.challerger.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler {

    public void handleApiError(String response) {
        if (response == null || response.isEmpty()) {
            throw new ApiException("Respuesta inválida de la API de tasa de cambio.");
        }

        try {
            // Validar estructura esperada de la respuesta
            JsonNode jsonResponse = new ObjectMapper().readTree(response);
            if (!jsonResponse.has("conversion_rate")) {
                throw new ApiException("La respuesta de la API no contiene la tasa de conversión esperada.");
            }
        } catch (JsonProcessingException e) {
            throw new ApiException("Error procesando la respuesta de la API.", e);
        }
    }

    public void handleApiError(String message, Throwable cause) {
        throw new ApiException(message, cause);
    }
}
