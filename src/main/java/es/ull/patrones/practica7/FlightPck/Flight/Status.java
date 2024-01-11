package es.ull.patrones.practica7.FlightPck.Flight;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Clase para representar las horas programada, estimada y real de salida([0]) y
 * llegada([1]) de un vuelo.
 */
public class Status {

    /**
     * Vector para almacenar los tiempos estimados de llegada y salida.
     */
    public Integer[] estimated = new Integer[2];

    /**
     * Vector para almacenar los tiempos reales de llegada y salida.
     */
    public Integer[] real = new Integer[2];

    /**
     * Vector para almacenar los tiempos programados de llegada y salida.
     */
    public Integer[] scheduled = new Integer[2];

    /**
     * Constructor de la clase Status.
     *
     * @param jsonNode Nodo JSON que contiene la información del estado del vuelo.
     */
    public Status(JsonNode jsonNode) {
        this.estimated[0] = jsonNode.get("estimated").get("departure").asInt();
        this.estimated[1] = jsonNode.get("estimated").get("arrival").asInt();
        this.real[0] = jsonNode.get("real").get("departure").asInt();
        this.real[1] = jsonNode.get("real").get("arrival").asInt();
        this.scheduled[0] = jsonNode.get("scheduled").get("departure").asInt();
        this.scheduled[1] = jsonNode.get("scheduled").get("arrival").asInt();
    }
}
