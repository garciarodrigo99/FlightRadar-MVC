package es.ull.patrones.practica7.FlightPck.EstadoVuelo;

import es.ull.patrones.practica7.FlightPck.Flight;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class OnAir extends Estado {
    public OnAir(Flight f) {
        super(f);
    }
    @Override
    public Estado checkEstado() {
        if (this.flight.getStatus().real[1] != null)
            return new Landed(this.flight);
        return this;
    }
    @Override
    public String toString() {
        return "On air";
    }

    @Override
    public String statusString() {
        return "Vuelo despegó a "+
                LocalDateTime.ofInstant(Instant.ofEpochSecond(this.flight.getStatus().real[0]), ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))+
                "Hora de llegada prevista: "+
                LocalDateTime.ofInstant(Instant.ofEpochSecond(this.flight.getStatus().estimated[1]), ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}

