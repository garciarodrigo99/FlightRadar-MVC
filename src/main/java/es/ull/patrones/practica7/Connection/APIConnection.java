package es.ull.patrones.practica7.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class APIConnection {
    /**
     * Obtiene el identificador de vuelo utilizando un script de Python.
     *
     * @param opcion   Opción que determina cómo comparar el valor
     *                 (1 para matrícula, 2 para número, 3 para identificador).
     * @param content  Contenido a comparar con la opción especificada.
     * @return         Identificador de vuelo obtenido del script de Python.
     */
    public static String getFlightId(String opcion, String content) {
        String outputContent = "1";
        try {
            String workingDir = System.getProperty("user.dir");
            // Ruta al script de Python
            String scriptPath = workingDir + "/get_flight_id.py";
            //System.out.println(scriptPath);
            // Parámetros para pasar al script de Python
            String[] scriptParameters = {opcion, content};

            // Crear un proceso para ejecutar el script
            ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath);
            processBuilder.command().addAll(Arrays.asList(scriptParameters));

            // Redirigir la salida estándar del proceso
            processBuilder.redirectErrorStream(true);

            // Iniciar el proceso
            Process process = processBuilder.start();

            // Capturar la salida del proceso
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outputBuilder.append(line).append(System.lineSeparator());
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            //System.out.println("Script de Python terminado con código de salida: " + exitCode);

            // Imprimir la salida del script de Python
            outputContent = outputBuilder.toString();
            //System.out.println("Salida del script de Python:" + outputContent);
            process.destroy();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return outputContent;
    }
    // Método que llama a desplegar el servicio web
    public static String loadConnection(String puerto, String opcion, String content) {
        String outputContent = "";
        try {
            String workingDir = System.getProperty("user.dir");
            // Ruta al script de Python
            String scriptPath = workingDir + "/api_service.py";
            //System.out.println(scriptPath);
            // Parámetros para pasar al script de Python
            String[] scriptParameters = {puerto,opcion,content};

            // Crear un proceso para ejecutar el script
            ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath);
            processBuilder.command().addAll(Arrays.asList(scriptParameters));

            // Redirigir la salida estándar del proceso
            processBuilder.redirectErrorStream(true);

            // Iniciar el proceso
            Process process = processBuilder.start();

            // Capturar la salida del proceso
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outputBuilder.append(line).append(System.lineSeparator());
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            //System.out.println("Script de Python terminado con código de salida: " + exitCode);

            // No Imprimir la salida del script de Python
            outputContent = outputBuilder.toString();
            System.out.println("Salida del script de Python:" + outputContent);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return outputContent;
    }
}
