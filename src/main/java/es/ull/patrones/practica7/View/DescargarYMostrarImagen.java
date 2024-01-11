package es.ull.patrones.practica7.View;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class DescargarYMostrarImagen {

    public static void main(String[] args) {
        String webURL = "http://www.gcmap.com";
        String mapGeneratorURL = webURL + "/mapui?P=c:%23ce0c87,TFN-LPA/MAD/LHR/JFK,MAD-BCN&MS=wls2";

        try {
            // Realizar la solicitud HTTP para obtener el contenido de la página
            Document doc = Jsoup.connect(mapGeneratorURL).get();

            // Seleccionar el elemento img dentro de la estructura de divs dada
            Element imgElement = doc.select("div#wrapper div#mid div#map_body.sect-show div#map_div img#map_image.map-image").first();

            // Verificar si se encontró el elemento y obtener el atributo 'src'
            if (imgElement != null) {
                String src = imgElement.attr("src");
                System.out.println("URL de la imagen: " + src);

                // Descargar la imagen y mostrarla en una ventana Swing
                descargarYMostrarImagen(webURL + src);
                System.out.println(webURL + src);
            } else {
                System.out.println("No se encontró la imagen en la estructura proporcionada.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void descargarYMostrarImagen(String imageUrl) {
        try {
            // Descargar la imagen desde la URL
            URL url = new URL(imageUrl);
            Image imagen = ImageIO.read(url);

            // Crear un JLabel para mostrar la imagen
            JLabel label = new JLabel(new ImageIcon(imagen));

            // Crear un JFrame para mostrar la imagen
            JFrame frame = new JFrame("Imagen Descargada");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(label);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image imagenfromURL(String imageUrl) {
        Image imagen = null;
        try {
            // Descargar la imagen desde la URL
            URL url = new URL(imageUrl);
            imagen = ImageIO.read(url);



        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }
}

