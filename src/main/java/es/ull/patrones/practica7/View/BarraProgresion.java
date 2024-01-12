package es.ull.patrones.practica7.View;

import javax.swing.*;
import java.awt.*;

public class BarraProgresion extends JPanel {
    private JProgressBar progressBar;
    private JLabel tiempoRestanteLabel;

    public BarraProgresion(int initialValor, int maxValor) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaciado alrededor del panel
        setBackground(Color.WHITE); // Fondo blanco

        // Crea una barra de progresión con un aspecto personalizado
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true); // Muestra el valor actual en la barra
        progressBar.setBorderPainted(false); // Borde no pintado
        progressBar.setBackground(new Color(240, 240, 240)); // Fondo gris claro
        progressBar.setForeground(new Color(30, 144, 255)); // Barra de progreso azul

        // Crea un JLabel para mostrar el tiempo restante
        tiempoRestanteLabel = new JLabel("Distancia restante: ");
        tiempoRestanteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tiempoRestanteLabel.setForeground(new Color(105, 105, 105)); // Gris oscuro


        // Añade la barra de progresión y el tiempo restante al panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(progressBar, BorderLayout.CENTER);
        centerPanel.add(tiempoRestanteLabel, BorderLayout.SOUTH);

        // Añade un espacio en blanco alrededor de la barra de progresión
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.add(centerPanel, BorderLayout.CENTER);

        progressBar.setMinimum(0);
        progressBar.setValue(initialValor);
        progressBar.setMaximum(maxValor);
        // Añade el panel de progresión al centro de la pantalla
        add(outerPanel, BorderLayout.CENTER);
    }

    // Método para actualizar la barra de progresión y mostrar el tiempo restante
    public void actualizarBarraProgresion(int valorActual) {

        progressBar.setValue(valorActual);

        int tiempoRestante = progressBar.getMaximum() - progressBar.getValue();
        tiempoRestanteLabel.setText("Distancia restante: " + tiempoRestante + " km");
        repaint();
    }
}
