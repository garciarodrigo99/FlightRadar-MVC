package es.ull.patrones.practica7.View;
import es.ull.patrones.practica7.FlightPck.Flight.Flight;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatosVueloView extends JPanel {
    private JLabel numeroVueloLabel;
    private JLabel matriculaLabel;
    private JLabel idVueloLabel;

    private JLabel aerolineaLabel;
    private JLabel tipoAeronaveLabel;
    private JLabel aeropuertoOrigenLabel;

    private JLabel aeropuertoDestinoLabel;
    private JLabel estadoVueloLabel;
    private JLabel velocidadAltitudLabel;
    private JLabel horaSalidaProgramadaLabel;
    private JLabel horaSalidaRealLabel;
    private JLabel horaLlegadaProgramadaLabel;
    private JLabel horaLlegadaRealLabel;

    public DatosVueloView(Flight myFlight) {
        // Configura la vista
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaciado alrededor del panel
        setBackground(new Color(240, 240, 240)); // Fondo del panel

        // Crea y configura los componentes
        numeroVueloLabel = createStyledLabel();
        matriculaLabel = createStyledLabel();
        idVueloLabel = createStyledLabel();
        aerolineaLabel = createStyledLabel();
        tipoAeronaveLabel = createStyledLabel();
        aeropuertoOrigenLabel = createStyledLabel();
        aeropuertoDestinoLabel = createStyledLabel();
        estadoVueloLabel = createStyledLabel();
        velocidadAltitudLabel = createStyledLabel();
        horaSalidaProgramadaLabel = createStyledLabel();
        horaSalidaRealLabel = createStyledLabel();
        horaLlegadaProgramadaLabel = createStyledLabel();
        horaLlegadaRealLabel = createStyledLabel();

        // Añade los componentes al panel
        add(createInfoPanel(myFlight), BorderLayout.CENTER);
        add(createImagePanel(), BorderLayout.EAST);
    }

    private JLabel createStyledLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(30, 144, 255)); // Azul

        return label;
    }

    private JPanel createInfoPanel(Flight myFlight) {
        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 0, 1)); // Cuadrícula de dos columnas
        infoPanel.setBackground(new Color(240, 240, 240)); // Fondo del panel

        // Agrega los componentes al panel
        infoPanel.add(createInfoLabel("Número de Vuelo:"));
        numeroVueloLabel.setText(myFlight.getfNumber());
        infoPanel.add(numeroVueloLabel);
        infoPanel.add(createInfoLabel("Matrícula:"));
        matriculaLabel.setText(myFlight.getRegistration());
        infoPanel.add(matriculaLabel);
        infoPanel.add(createInfoLabel("ID:"));
        idVueloLabel.setText(myFlight.getId());
        infoPanel.add(idVueloLabel);
        infoPanel.add(createInfoLabel("Aerolínea:"));
        aerolineaLabel.setText("1234");
        infoPanel.add(aerolineaLabel);
        infoPanel.add(createInfoLabel("Tipo de Aeronave:"));
        tipoAeronaveLabel.setText("1234");
        infoPanel.add(tipoAeronaveLabel);
        infoPanel.add(createInfoLabel("Origen:"));
        aeropuertoOrigenLabel.setText(myFlight.getOrigin().getCode().getIata());
        infoPanel.add(aeropuertoOrigenLabel);
        infoPanel.add(createInfoLabel("Destino:"));
        aeropuertoDestinoLabel.setText(myFlight.getDestination().getCode().getIata());
        infoPanel.add(aeropuertoDestinoLabel);
        infoPanel.add(createInfoLabel("Velocidad(knots) / Altitud(ft):"));
        velocidadAltitudLabel.setText(String.valueOf(myFlight.getSpeed())+
                " / "+
                String.valueOf(myFlight.getAltitud()));
        infoPanel.add(velocidadAltitudLabel);
        infoPanel.add(createInfoLabel("Hora programada salida:"));
        horaSalidaProgramadaLabel.setText("1234");
        infoPanel.add(horaSalidaProgramadaLabel);
        infoPanel.add(createInfoLabel("Hora real salida:"));
        horaSalidaRealLabel.setText("1234");
        infoPanel.add(horaSalidaRealLabel);
        infoPanel.add(createInfoLabel("Hora programada llegada:"));
        horaLlegadaProgramadaLabel.setText("1234");
        infoPanel.add(horaLlegadaProgramadaLabel);
        infoPanel.add(createInfoLabel("Hora real llegada:"));
        horaLlegadaRealLabel.setText("1234");
        infoPanel.add(horaLlegadaRealLabel);

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
    public void actualizarInformacionVuelo(String velocidadAltitud, String numeroRegistro,
                                           String fabricanteModelo, Date fechaHora) {
        velocidadAltitudLabel.setText(velocidadAltitud);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //fechaHoraLabel.setText(dateFormat.format(fechaHora));
    }
}
