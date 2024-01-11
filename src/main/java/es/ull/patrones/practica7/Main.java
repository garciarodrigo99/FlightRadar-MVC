package es.ull.patrones.practica7;

import es.ull.patrones.practica7.Connection.APIConnection;
import es.ull.patrones.practica7.Connection.FreePortFinder;
import es.ull.patrones.practica7.Controler.ControladorVuelo;
import es.ull.patrones.practica7.Model.Flight.Flight;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String flightID = APIConnection.getFlightId("2", "VY3066");
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

        Flight myFlight = new Flight(flightID,puerto);

        ControladorVuelo controlador = new ControladorVuelo(myFlight);
        controlador.mostrar();

        apiThread.interrupt();

    }
    // Método para mostrar una ventana de error y cerrar el programa
    private static void mostrarVentanaError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
}