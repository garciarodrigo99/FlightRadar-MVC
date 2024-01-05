package es.ull.patrones.practica7.Connection;

import java.io.IOException;
import java.net.ServerSocket;

public class FreePortFinder {

    public static int findFreePort(int startingPort) {
        int maxPort = 65535; // Puerto máximo

        for (int port = startingPort; port <= maxPort; port++) {
            if (isPortAvailable(port)) {
                return port;
            }
        }

        throw new RuntimeException("No se encontró ningún puerto libre después de " + startingPort);
    }

    public static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return true;
        } catch (IOException e) {
            // El puerto no está disponible
            return false;
        }
    }
}
