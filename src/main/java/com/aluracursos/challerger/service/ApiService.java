package com.aluracursos.challerger.service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class ApiService {

    private static final String API_URL = "https://api.exchangeratesapi.io/latest?base=USD";
    public JsonObject obtenerTasaCambio(String monedaBase) throws IOException { OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(API_URL.replace("USD", monedaBase)).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String jsonResponse = response.body().string();
            return JsonParser.parseString(jsonResponse).getAsJsonObject();
        }
    }
}
