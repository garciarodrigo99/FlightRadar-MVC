package es.ull.patrones.practica7.Events;


import es.ull.patrones.practica7.Model.*;

import java.util.*;


public class EventManager {
    Map<suscriptionObject, List<myEventListener>> listeners = new HashMap<>();

    public EventManager(suscriptionObject so,myEventListener el) {
        this.listeners.put(so, Arrays.asList(el));
        notifyObservers(so,so.getInitialMessage());
    }

    // Método para agregar observadores. Si ya existe un suscriptionObject, se añade el myEventListener a la lista
    // existente de observadores asociados a ese suscriptionObject. Si el suscriptionObject no existe, se crea una
    // nueva entrada en el mapa.
    public void addObserver(suscriptionObject so,myEventListener el) {
        List<myEventListener> observers = listeners.getOrDefault(so, new ArrayList<>());
        observers.add(el);
        listeners.put(so, observers);
    }

    // Método para quitar observadores
    public void removeObserver(suscriptionObject so,myEventListener el) {
        List<myEventListener> observers = listeners.getOrDefault(so, new ArrayList<>());
        observers.remove(el);

        // Actualiza la lista asociada a la clave
        listeners.put(so, observers);
    }

    // Método para notificar a los observadores
    public void notifyObservers(suscriptionObject so, String mensaje) {
        List<myEventListener> observers = listeners.getOrDefault(so, new ArrayList<>());

        for (myEventListener el : observers) {
            // Realizar acciones con cada observador
            el.update(mensaje);
        }
    }

    public void checkEvents() throws InterruptedException {
        while(true){
            for (suscriptionObject so : listeners.keySet()) {
                String aviso = so.checkInformation();
                if (aviso != null){
                    System.out.println("checkEvents: "+aviso);
                    notifyObservers(so,aviso);
                }
            }
            Thread.sleep(10000);
        }

    }

}
