package es.ull.patrones.practica7.Connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadJsonFromUrl {
    public static JsonNode read(String url) {
        JsonNode jsonNode = null;
        try {
            // URL del JSON
/*            String url = "https://ejemplo.com/tu-archivo.json";*/

            // Abrir conexión y leer JSON
            String json = readJsonFromUrl(url);

            // Analizar el JSON
            ObjectMapper objectMapper = new ObjectMapper();
            //JsonNode jsonNode = objectMapper.readTree(json);
            jsonNode = objectMapper.readTree(json);

            // Acceder a los datos del JSON
/*            String valor = jsonNode.get("clave").asText();
            System.out.println("Valor de la clave: " + valor);*/

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    private static String readJsonFromUrl(String url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            return jsonContent.toString();
        }
    }
}
