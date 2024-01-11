package es.ull.patrones.practica7.View;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class mostrarImagenes {

    public static Image imagenFromDestinos(String destinos) {
        String webURL = "http://www.gcmap.com";
        String mapGeneratorURL = webURL + "/mapui?P=c:%23ce0c87,"+destinos+"&MS=wls2";
        Image imagen = null;
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
                URL url = new URL(webURL + src);
                imagen = ImageIO.read(url);
                System.out.println(url);
            } else {
                System.out.println("No se encontró la imagen en la estructura proporcionada.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
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

