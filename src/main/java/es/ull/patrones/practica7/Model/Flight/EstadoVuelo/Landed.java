package es.ull.patrones.practica7.Model.Flight.EstadoVuelo;

import es.ull.patrones.practica7.Model.Flight.Flight;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Landed extends Estado {
    public Landed(Flight f) {
        super(f);
    }

    @Override
    public Estado checkEstado() {
        return this;
    }

    @Override
    public String toString() {
        return "Landed";
    }
    @Override
    public String statusString() {
        return "\nVuelo aterrizó a "+
                LocalDateTime.ofInstant(Instant.ofEpochSecond(this.flight.getStatus().real[1]), ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))+"\n";
    }
}
