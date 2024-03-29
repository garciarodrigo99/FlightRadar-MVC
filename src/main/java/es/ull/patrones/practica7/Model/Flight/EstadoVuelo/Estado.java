package es.ull.patrones.practica7.Model.Flight.EstadoVuelo;

import es.ull.patrones.practica7.Model.Flight.Flight;

public abstract class Estado {

    protected Flight flight;
    public Estado (Flight f){
        this.flight = f;
    }

    public abstract Estado checkEstado();

    @Override
    public abstract String toString();
    public abstract String statusString();
}
