package es.ull.patrones.practica7;

import es.ull.patrones.practica7.Events.EventManager;

public class TrackerApp {
    public EventManager events;

    // Constructor, getters y setters
    public TrackerApp(EventManager events){
        this.events = events;
    }


    public void start() throws InterruptedException {
        events.checkEvents();
    }
}
