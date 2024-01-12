package es.ull.patrones.practica7.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class Menu extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JComboBox<String> opcionesComboBox;
    private JTextField cuadroTexto;
    private JButton botonAccion;
    private String opcionSeleccionada;
    private String textoIngresado;
    private CountDownLatch latch = new CountDownLatch(1);

    public Menu() {
        setPreferredSize(new Dimension(400, 300));
        pack();
        setTitle("Selección de vuelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel entradaPanel = new JPanel(new GridLayout(4, 1));

        String[] opciones = {"Matrícula", "Número de vuelo", "ID de vuelo"};
        opcionesComboBox = new JComboBox<>(opciones);

        cuadroTexto = new JTextField();

        botonAccion = new JButton("Continuar");
        botonAccion.setBackground(new Color(175, 197, 219));
        botonAccion.setForeground(Color.BLACK);
        botonAccion.setFocusPainted(false);

        botonAccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcionSeleccionada = (String) opcionesComboBox.getSelectedItem();
                textoIngresado = cuadroTexto.getText();
                latch.countDown();  // Liberar el CountDownLatch
            }
        });

        entradaPanel.add(opcionesComboBox);
        entradaPanel.add(cuadroTexto);
        entradaPanel.add(botonAccion);

        cardPanel.add(entradaPanel, "ENTRADA");

        getContentPane().add(cardPanel);
    }

    public String getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    public String getTextoIngresado() {
        return textoIngresado;
    }

    public void mostrarVentana() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }

    // Método para esperar a que el usuario complete la acción
    public void esperarAccion() {
        try {
            // Esperar hasta que el usuario complete la acción
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ocultar la ventana después de que el usuario haya completado la acción
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
        });
    }
}