package es.ull.patrones.practica7.Model.Flight;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.patrones.practica7.Connection.ReadJsonFromUrl;
import es.ull.patrones.practica7.DateFormat;
import es.ull.patrones.practica7.Model.Airport.Airport;
import es.ull.patrones.practica7.Model.Distancia;
import es.ull.patrones.practica7.Model.Flight.EstadoVuelo.BeforeTO;
import es.ull.patrones.practica7.Model.Flight.EstadoVuelo.Estado;
import es.ull.patrones.practica7.Model.Flight.EstadoVuelo.Landed;
import es.ull.patrones.practica7.Model.Flight.EstadoVuelo.OnAir;
import es.ull.patrones.practica7.Model.suscriptionObject;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Flight implements suscriptionObject {

    private String id;
    private String registration;
    private String fNumber;
    private Airport origin;
    private Airport destination;
    private int distanciaVuelo;
    private Status status;
    protected List<Pair<Long,Integer>> listaVelocidad = new ArrayList<>();
    protected List<Pair<Long,Integer>> listaAltitud = new ArrayList<>();
    private String airlineICAO;
    private String aircraftCode;
    private int speed;
    private int altitud;
    private String history;

    protected String serverURL;

    protected String infoURL;

    protected String airportsURL;

    protected String statusURL;

    protected Estado estado;

    protected String recentFlightsImageURL;
    private String trailURL;

    public Flight(String id,String urlPort) {
        // Eliminar salto de linea y espacio en blaco
        this.id = id.substring(0, id.length() - 1);
        
        this.serverURL = "http://127.0.0.1:"+urlPort+"/"+this.id;

        setInfoData();

        setAirportsData();

        setStatusData();
        
        setHistoryData();

        setTrail();
    }

    private void setInfoData(){
        this.infoURL = this.serverURL+"/info";
        JsonNode infoJsonNode = ReadJsonFromUrl.read(this.infoURL);
        this.registration = infoJsonNode.get("registration").asText();
        this.fNumber = infoJsonNode.get("number").asText();
        this.airlineICAO = infoJsonNode.get("airline_icao").asText();
        this.aircraftCode = infoJsonNode.get("aircraft_code").asText();
        this.speed = infoJsonNode.get("ground_speed").asInt();
        this.altitud = infoJsonNode.get("altitude").asInt();
    }

    private void setTrail() {
        this.trailURL = this.serverURL+"/trail";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode trailJsonNode = ReadJsonFromUrl.read(this.trailURL);

            for (JsonNode node : trailJsonNode) {
                Integer alt = node.get("alt").asInt();
                Integer spd = node.get("spd").asInt();
                Long ts = node.get("ts").asLong();

                listaAltitud.add(Pair.of(ts,alt));
                listaVelocidad.add(Pair.of(ts,spd));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAirportsData() {
        this.airportsURL = this.serverURL+"/airports";
        JsonNode airportsJsonNode = ReadJsonFromUrl.read(this.airportsURL);
        this.origin = new Airport(airportsJsonNode.get("origin"));
        this.destination = new Airport(airportsJsonNode.get("destination"));
        this.distanciaVuelo = Distancia.calcularDistancia(this.origin.getPosition(),
                this.destination.getPosition());
    }

    private void setStatusData(){
        this.statusURL = this.serverURL+"/status";
        this.status = new Status(ReadJsonFromUrl.read(this.statusURL));

        if (this.status.real[0] == 0){
            this.estado = new BeforeTO(this);
        } else if (this.status.real[1] == 0){
            this.estado = new OnAir(this);
        } else {
            this.estado = new Landed(this);
        }
    }

    private void setHistoryData(){
        JsonNode historyJsonNode = ReadJsonFromUrl.read(this.serverURL+"/history");
        //String allRoutes = "c:#01b3a8,";
        String allRoutes = "";
        // Recorrer el JsonNode e imprimir cada elemento
        System.out.println("Recorriendo vector de rutas:");
        for (JsonNode nodo : historyJsonNode) {
            String ruta = nodo.asText();
            allRoutes += ruta + ',';
            System.out.println(ruta);
        }
        System.out.println(allRoutes);
        this.history = allRoutes;
    }

    public String getId(){
        return this.id;
    }
    public String getRegistration() {
        return registration;
    }

    public String getfNumber() {
        return fNumber;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public List<Pair<Long, Integer>> getListaVelocidad() {
        return listaVelocidad;
    }

    public List<Pair<Long, Integer>> getListaAltitud() {
        return listaAltitud;
    }

    public int getDistanciaVuelo() {
        return distanciaVuelo;
    }

    public Status getStatus(){
        return this.status;
    }

    public String getAirlineICAO() {
        return airlineICAO;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAltitud() {
        return altitud;
    }

    @Override
    public String getInitialMessage() {
        String to_return = "-Monitorizando vuelo-\n" +
                "Id: " + this.id + "\n" +
                "Matricula: " + this.registration + "\n" +
                "Número de vuelo: " + this.fNumber + "\n" +
                this.origin.getCode().getIata() + "-" + this.destination.getCode().getIata() + "\n" +
                "Distancia de vuelo: " + this.distanciaVuelo + "km\n" +
                "Status:\n";
        if (this.status.estimated[0] != 0) {
            System.out.println("estimated departure: " + this.status.estimated[0]);
            to_return += " - Estimated Departure: " + DateFormat.getFormatedDate(this.status.estimated[0]) + "\n";
        }
        if (this.status.estimated[1] != 0) {
            to_return += " - Estimated Arrival: " + DateFormat.getFormatedDate(this.status.estimated[1]) + "\n";
        }
        if (this.status.real[0] != 0){
            to_return += " - Real Departure: " + DateFormat.getFormatedDate(this.status.real[0]) + "\n";
        }
        if (this.status.real[1] != 0){
            to_return += " - Real Arrival: " + DateFormat.getFormatedDate(this.status.real[1]) + "\n";
        }
        if (this.status.scheduled[0] != 0){
            to_return += " - Scheduled Departure: " + DateFormat.getFormatedDate(this.status.scheduled[0]) + "\n";
        }
        if (this.status.scheduled[1] != 0){
            to_return += " - Scheduled Arrival: " + DateFormat.getFormatedDate(this.status.scheduled[1]);
        }

        return to_return;
    }

    private void updateStatus(){
        this.status = new Status(ReadJsonFromUrl.read(this.statusURL));
    }


    public String getRecentFlightsImageURL() {
        String webURL = "http://www.gcmap.com";
        String mapGeneratorURL = webURL + "/mapui?P=c:%23ce0c87,"+this.history +"&MS=wls2";
        //Image imagen = null;
        try {
            // Realizar la solicitud HTTP para obtener el contenido de la página
            Document doc = Jsoup.connect(mapGeneratorURL).get();

            // Seleccionar el elemento img dentro de la estructura de divs dada
            Element imgElement = doc.select("div#wrapper div#mid div#map_body.sect-show div#map_div img#map_image.map-image").first();

            // Verificar si se encontró el elemento y obtener el atributo 'src'
            if (imgElement != null) {
                String src = imgElement.attr("src");
                System.out.println("URL de la imagen: " + src);

                // Descargar la imagen y mostrarla en una ventana Swing
                URL url = new URL(webURL + src);

                this.recentFlightsImageURL = String.valueOf(url);
                //imagen = ImageIO.read(url);
                System.out.println(url);
            } else {
                System.out.println("No se encontró la imagen en la estructura proporcionada.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recentFlightsImageURL;
    }

    @Override
    public String checkInformation(){
        String lastEstado = this.estado.toString();
        System.out.println("Estado del vuelo: "+lastEstado);
        updateStatus();
        this.estado = this.estado.checkEstado();
        if(!lastEstado.equals(this.estado.toString())){
            System.out.println("checkInformation: "+this.estado.statusString());
            return this.estado.statusString();
        }
        return null;
    }

}
