package es.ull.patrones.practica7.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Vista para el panel de imagen
class ImagenView extends JPanel {

       public ImagenView(Image imagen) {

            // Crear un JLabel para mostrar la imagen
            JLabel label = new JLabel(new ImageIcon(imagen));

            // Agregar el JLabel al JPanel
            add(label);
        }
}