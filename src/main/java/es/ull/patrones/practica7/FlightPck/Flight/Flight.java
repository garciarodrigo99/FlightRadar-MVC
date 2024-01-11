package es.ull.patrones.practica7.FlightPck.Flight;

import com.fasterxml.jackson.databind.JsonNode;
import es.ull.patrones.practica7.Connection.ReadJsonFromUrl;
import es.ull.patrones.practica7.DateFormat;
import es.ull.patrones.practica7.FlightPck.Airport.Airport;
import es.ull.patrones.practica7.FlightPck.Flight.EstadoVuelo.BeforeTO;
import es.ull.patrones.practica7.FlightPck.Flight.EstadoVuelo.Estado;
import es.ull.patrones.practica7.FlightPck.Flight.EstadoVuelo.Landed;
import es.ull.patrones.practica7.FlightPck.Flight.EstadoVuelo.OnAir;
import es.ull.patrones.practica7.FlightPck.suscriptionObject;

public class Flight implements suscriptionObject {
    private String id;
    private String registration;
    private String fNumber;
    private Airport origin;
    private Airport destination;
    private Status status;

    protected String serverURL;

    protected String infoURL;

    protected String airportsURL;

    protected String statusURL;

    protected Estado estado;

    protected String recentFlightsImageURL;

    public Flight(String id,String urlPort) {
        // Eliminar salto de linea y espacio en blaco
        this.id = id.substring(0, id.length() - 1);
        
        this.serverURL = "http://127.0.0.1:"+urlPort+"/"+this.id;

        setInfoData();

        setAirportsData();

        setStatusData();
        
        setHistoryData();
    }

    private void setInfoData(){
        this.infoURL = this.serverURL+"/info";
        JsonNode infoJsonNode = ReadJsonFromUrl.read(this.infoURL);
        this.registration = infoJsonNode.get("registration").asText();
        this.fNumber = infoJsonNode.get("number").asText();
    }

    private void setAirportsData() {
        this.airportsURL = this.serverURL+"/airports";
        JsonNode airportsJsonNode = ReadJsonFromUrl.read(this.airportsURL);
        this.origin = new Airport(airportsJsonNode.get("origin"));
        this.destination = new Airport(airportsJsonNode.get("destination"));
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
        String allRoutes = "c:#01b3a8,";
        // Recorrer el JsonNode e imprimir cada elemento
        System.out.println("Recorriendo vector de rutas:");
        for (JsonNode nodo : historyJsonNode) {
            String ruta = nodo.asText();
            allRoutes += ruta + ',';
            System.out.println(ruta);
        }
        System.out.println(allRoutes);
    }

    public String getId(){
        return this.id;
    }

    public Status getStatus(){
        return this.status;
    }
    @Override
    public String getInitialMessage() {
        String to_return = "-Monitorizando vuelo-\n" +
                "Id: " + this.id + "\n" +
                "Matricula: " + this.registration + "\n" +
                "Número de vuelo: " + this.fNumber + "\n" +
                this.origin.getCode().getIata() + "-" + this.destination.getCode().getIata() + "\n" +
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
