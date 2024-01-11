package es.ull.patrones.practica7.Model;

public class Distancia {
    public static int calcularDistancia(Position origen, Position destino){
        // Radio de la Tierra en kilómetros
        final double RADIO_TIERRA = 6378.0;

        // Convertir las coordenadas de grados a radianes
        double latitud1Rad = Math.toRadians(origen.getLatitude());
        double longitud1Rad = Math.toRadians(origen.getLongitude());
        double latitud2Rad = Math.toRadians(destino.getLatitude());
        double longitud2Rad = Math.toRadians(destino.getLongitude());

        // Calcular la diferencia en las coordenadas
        double deltaLatitud = latitud2Rad - latitud1Rad;
        double deltaLongitud = longitud2Rad - longitud1Rad;

        // Aplicar la fórmula de Haversine
        double a = Math.sin(deltaLatitud / 2.0) * Math.sin(deltaLatitud / 2.0) +
                Math.cos(latitud1Rad) * Math.cos(latitud2Rad) *
                        Math.sin(deltaLongitud / 2.0) * Math.sin(deltaLongitud / 2.0);

        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));

        // Calcular la distancia y redondear al entero más cercano
        int distancia = (int) Math.round(RADIO_TIERRA * c);

        return distancia;
    }
}
