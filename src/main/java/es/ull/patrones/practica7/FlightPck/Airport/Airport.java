package es.ull.patrones.practica7.FlightPck.Airport;

import com.fasterxml.jackson.databind.JsonNode;
import es.ull.patrones.practica7.FlightPck.Country;
import es.ull.patrones.practica7.FlightPck.Position;
import es.ull.patrones.practica7.FlightPck.Timezone;

public class Airport {
    private String name;
    private AirportCode code;
    private Position position;
    private Country country;
    private String city;
    private Timezone timezone;

    public Airport(JsonNode airportNode){
        this.name = airportNode.get("name").asText();
        this.code = new AirportCode(airportNode.get("code").get("iata").asText());

        JsonNode positionNode = airportNode.get("position");
        this.city = positionNode.get("region").get("city").asText();
        this.position = new Position(positionNode.get("latitude").asDouble(),
                positionNode.get("longitude").asDouble(),
                positionNode.get("altitude").asInt());

        JsonNode countryNode = positionNode.get("country");
        this.country = new Country(countryNode.get("id").asInt(),
                countryNode.get("name").asText(),
                countryNode.get("code").asText(),
                countryNode.get("codeLong").asText());



        JsonNode timezoneNode = airportNode.get("timezone");
        this.timezone = new Timezone(timezoneNode.get("name").asText(),
                timezoneNode.get("offset").asInt(),
                timezoneNode.get("offsetHours").asText(),
                timezoneNode.get("abbr").asText(),
                timezoneNode.get("abbrName").asText(),
                timezoneNode.get("isDst").asBoolean());

    }
    public String getName() {
        return name;
    }

    public AirportCode getCode() {
        return code;
    }

    public Position getPosition() {
        return position;
    }

    public Country getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", position=" + position +
                ", country=" + country +
                ", city='" + city + '\'' +
                ", timezone=" + timezone +
                '}';
    }
}
