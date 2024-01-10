package es.ull.patrones.practica7;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private JPanel panel;
    private JScrollPane scrollPane;

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
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Configura el JScrollPane con barras de desplazamiento en ambas direcciones
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void mostrarMensaje(String mensaje) {

/*        System.out.println("Cadena original: " + mensaje);
        System.out.println("ASCII de cada carácter:");

        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);
            int asciiValue = (int) caracter;
            System.out.println("'" + caracter + "': " + asciiValue);
        }*/

        JLabel nuevaLabel = new JLabel(mensaje);
        panel.add(nuevaLabel);

        // Asegura que el JScrollPane se desplace hacia abajo y hacia los lados automáticamente
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setValue(horizontalScrollBar.getMaximum());

        pack();
        setLocationRelativeTo(null);
    }
}