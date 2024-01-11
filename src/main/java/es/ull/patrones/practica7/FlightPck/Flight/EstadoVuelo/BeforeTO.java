package es.ull.patrones.practica7.FlightPck.EstadoVuelo;

import es.ull.patrones.practica7.FlightPck.Flight;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class BeforeTO extends Estado {
    public BeforeTO(Flight f) {
        super(f);
    }

    @Override
    public Estado checkEstado() {
        if (this.flight.getStatus().real[0] != 0)
            return new OnAir(this.flight);
        return this;
    }
    @Override
    public String toString() {
        return "Before Take Off";
    }

    @Override
    public String statusString() {
        return "Se prevé el despegue a: "+
                LocalDateTime.ofInstant(Instant.ofEpochSecond(this.flight.getStatus().estimated[0]), ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}

