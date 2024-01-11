package es.ull.patrones.practica7.FlightPck.Airport;

public class AirportCode {
    private String iata;

    public AirportCode(String iata) {
        this.iata = iata;
    }

    public String getIata() {
        return iata;
    }

    @Override
    public String toString() {
        return "AirportCode[" +
                "iata='" + iata + '\'' +
                '}';
    }
}
