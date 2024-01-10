package es.ull.patrones.practica7;

import es.ull.patrones.practica7.Connection.APIConnection;
import es.ull.patrones.practica7.Connection.FreePortFinder;
import es.ull.patrones.practica7.Events.*;
import es.ull.patrones.practica7.FlightPck.Flight;

import javax.swing.*;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String flightID = APIConnection.getFlightId("2", "BA2703");
        System.out.println("Flight ID: " + flightID);
        if (flightID.charAt(0) == '1') {
            mostrarVentanaError("Error seguimiento de vuelo", "No se ha encontrado ningún vuelo en vivo para \nser rastreado con la información proporcionada.");
            System.exit(2);  // Terminar el programa
        }
        String puerto = String.valueOf(FreePortFinder.findFreePort(5000));
        System.out.println("Puerto asignado: "+puerto);
        // Lanzar servicio web local para un número de vuelo y poder consultar
        Thread apiThread = new Thread(() -> APIConnection.loadConnection(puerto,"-i",flightID));
        apiThread.start();
        Thread.sleep(10000);
        Usuario rodrigo = new Usuario("Rodrigo");
        Usuario enrique = new Usuario("Enrique");

        Flight mad_eze = new Flight(flightID,puerto);
        //TrackerApp editor = new TrackerApp(mad_eze.getId());
        List<Usuario> userlist = Arrays.asList(rodrigo,enrique);
        myEventListener flight1Listener = new FlightListener(userlist);
        EventManager em = new EventManager(mad_eze,flight1Listener);
        TrackerApp ta = new TrackerApp(em);
        ta.start();
        apiThread.interrupt();

    }
    // Método para mostrar una ventana de error y cerrar el programa
    private static void mostrarVentanaError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
}