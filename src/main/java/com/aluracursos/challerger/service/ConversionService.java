package com.aluracursos.challerger.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class ConversionService {

    @Value("${api.exchangerate.url}")
    private String apiUrl;

    @Value("${api.exchangerate.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ConversionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double obtenerTasaDeConversion(String from, String to) {
        try {
            String url = String.format("%s/%s/latest/%s", apiUrl, apiKey, from);
            ConversionResponse response = restTemplate.getForObject(url, ConversionResponse.class);

            if (response == null || response.getRates() == null) {
                throw new IllegalArgumentException("Respuesta inválida de la API de tasa de cambio.");
            }

            Double rate = response.getRates().getRates().get(to);

            if (rate == null) {
                throw new IllegalArgumentException("No se encontró la tasa de conversión para la moneda especificada.");
            }

            return rate;
        } catch (RestClientException e) {
            throw new RuntimeException("Error al comunicarse con la API de tasa de cambio.", e);
        }
    }

    static class ConversionResponse {
        private Rates rates;

        public Rates getRates() {
            return rates;
        }

        public void setRates(Rates rates) {
            this.rates = rates;
        }

        static class Rates {
            private Map<String, Double> rates;

            public Map<String, Double> getRates() {
                return rates;
            }

            public void setRates(Map<String, Double> rates) {
                this.rates = rates;
            }
        }
    }
}
