package es.ull.patrones.practica7.Events;

import es.ull.patrones.practica7.FlightPck.Flight;
import es.ull.patrones.practica7.Usuario;

import java.util.List;

public class FlightListener implements myEventListener {

    private List<Usuario> usuarios;

    public FlightListener(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    public void addUsuario(Usuario usario){
        this.usuarios.add(usario);
    }

    @Override
    public void update(String flightInfo) {
        for (Usuario user : this.usuarios) {
            user.recibirAviso(flightInfo);
        }
    }
}
