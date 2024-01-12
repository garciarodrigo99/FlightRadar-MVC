package es.ull.patrones.practica7.MainMenu;

import java.util.HashMap;
import java.util.Map;

public class FactoryOptions {
    private static final Map<String, String> opciones;

    static {
        // Inicializa el HashMap con las transformaciones deseadas
        opciones = new HashMap<>();
        opciones.put("Matrícula", "1");
        opciones.put("Número de vuelo", "2");
        opciones.put("ID de vuelo", "3");
        // Agrega más transformaciones según sea necesario
    }
    public static String transformarCadena(String entrada) {
        // Busca la cadena en el HashMap y devuelve la transformación, o la cadena original si no hay transformación
        return opciones.getOrDefault(entrada, entrada);
    }
}
