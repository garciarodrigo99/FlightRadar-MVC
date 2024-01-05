package es.ull.patrones.practica7;

public class Usuario {

    private Ventana ventana;
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.ventana = new Ventana(this.nombre);
    }

    public void recibirAviso(String aviso){
        ventana.mostrarMensaje(aviso);
    }

}
