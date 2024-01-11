package es.ull.patrones.practica7.View;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatosVueloView extends JPanel {
    private JLabel numeroVueloLabel;
    private JLabel aerolineaLabel;
    private JLabel tipoAeronaveLabel;
    private JLabel estadoVueloLabel;
    private JLabel velocidadAltitudLabel;
    private JLabel registroLabel;
    private JLabel fabricanteModeloLabel;
    private JLabel fechaHoraLabel;

    DatosVueloView() {
        // Configura la vista
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaciado alrededor del panel
        setBackground(new Color(240, 240, 240)); // Fondo del panel

        // Crea y configura los componentes
        numeroVueloLabel = createStyledLabel();
        aerolineaLabel = createStyledLabel();
        tipoAeronaveLabel = createStyledLabel();
        estadoVueloLabel = createStyledLabel();
        velocidadAltitudLabel = createStyledLabel();
        registroLabel = createStyledLabel();
        fabricanteModeloLabel = createStyledLabel();
        fechaHoraLabel = createStyledLabel();

        // Añade los componentes al panel
        add(createInfoPanel(), BorderLayout.CENTER);
        add(createImagePanel(), BorderLayout.EAST);
    }

    private JLabel createStyledLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(30, 144, 255)); // Azul

        return label;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 5, 10)); // Cuadrícula de dos columnas
        infoPanel.setBackground(new Color(240, 240, 240)); // Fondo del panel

        // Agrega los componentes al panel
        infoPanel.add(createInfoLabel("Número de Vuelo:"));
        infoPanel.add(numeroVueloLabel);
        infoPanel.add(createInfoLabel("Aerolínea:"));
        infoPanel.add(aerolineaLabel);
        infoPanel.add(createInfoLabel("Tipo de Aeronave:"));
        infoPanel.add(tipoAeronaveLabel);
        infoPanel.add(createInfoLabel("Estado del Vuelo:"));
        infoPanel.add(estadoVueloLabel);
        infoPanel.add(createInfoLabel("Velocidad / Altitud:"));
        infoPanel.add(velocidadAltitudLabel);
        infoPanel.add(createInfoLabel("Número de Registro:"));
        infoPanel.add(registroLabel);
        infoPanel.add(createInfoLabel("Fabricante / Modelo:"));
        infoPanel.add(fabricanteModeloLabel);
        infoPanel.add(createInfoLabel("Fecha y Hora:"));
        infoPanel.add(fechaHoraLabel);


        return infoPanel;
    }

    private JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(105, 105, 105)); // Gris oscuro
        return label;
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel();
        // Puedes añadir la lógica para mostrar la imagen aquí
        imagePanel.setBackground(new Color(240, 240, 240)); // Fondo del panel

        return imagePanel;
    }

    // Método para actualizar la información del vuelo en la vista
    public void actualizarInformacionVuelo(String numeroVuelo, String aerolinea, String tipoAeronave,
                                           String estadoVuelo, String velocidadAltitud, String numeroRegistro,
                                           String fabricanteModelo, Date fechaHora) {
        numeroVueloLabel.setText(numeroVuelo);
        aerolineaLabel.setText(aerolinea);
        tipoAeronaveLabel.setText(tipoAeronave);
        estadoVueloLabel.setText(estadoVuelo);
        velocidadAltitudLabel.setText(velocidadAltitud);
        registroLabel.setText(numeroRegistro);
        fabricanteModeloLabel.setText(fabricanteModelo);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        fechaHoraLabel.setText(dateFormat.format(fechaHora));
    }
}
