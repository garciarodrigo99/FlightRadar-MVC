package es.ull.patrones.practica7;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private JLabel label;

    private String nombreUsuario;  // Agrega una variable para almacenar el nombre del usuario

    public Ventana(String nombreUsuario) {
        super(nombreUsuario);  // Usa el nombre del usuario en el título
        this.nombreUsuario = nombreUsuario;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        label = new JLabel();
        add(label, BorderLayout.CENTER);
    }

    public void mostrarMensaje(String mensaje) {
        String textoActual = label.getText();
        String nuevoTexto = textoActual + "\n" + mensaje;
        label.setText(nuevoTexto);
    }
}