package es.ull.patrones.practica7;

import es.ull.patrones.practica7.Connection.APIConnection;
import es.ull.patrones.practica7.Connection.FreePortFinder;
import es.ull.patrones.practica7.Events.*;
import es.ull.patrones.practica7.FlightPck.Flight.Flight;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
/*
        TimeSeriesChart chart = new TimeSeriesChart("Ejemplo de Gráfico de Hora de Unix",
                "Hora",
                "Velocidad");
        chart.insertarDatos(1704911708, 10);
        chart.insertarDatos(1704911641, 20);
        chart.insertarDatos(1704981775, 30);
        chart.mostrar();*/

        String flightID = APIConnection.getFlightId("2", "TP57");
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
        TimeSeriesChart chart = new TimeSeriesChart("Ejemplo de Gráfico de Hora de Unix",
                "Hora",
                "Altitud(ft)");
        for (Pair<Long,Integer> reg : mad_eze.getListaAltitud()){
            chart.insertarDatos(reg.getLeft(),reg.getRight());
        }
        /*chart.insertarDatos(1704911708, 10);
        chart.insertarDatos(1704911641, 20);
        chart.insertarDatos(1704981775, 30);*/
        chart.mostrar();
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