package es.ull.patrones.practica7.Controler;
import es.ull.patrones.practica7.Model.Flight.Flight;
import es.ull.patrones.practica7.View.TimeSeriesChartPanel;
import es.ull.patrones.practica7.View.BarraProgresion;
import es.ull.patrones.practica7.View.DatosVueloView;
import es.ull.patrones.practica7.View.mostrarImagenes;
import es.ull.patrones.practica7.View.ImagenView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class ControladorVuelo extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private DatosVueloView datosVueloView;

    private ImagenView imagenView;
    private BarraProgresion barraProgresion;
    private Flight flightSelected;

    private TimeSeriesChartPanel chartVelocidad;
    private TimeSeriesChartPanel chartAltitud;

    public ControladorVuelo(Flight flightSelected) {
        this.flightSelected = flightSelected;
        // Configuración de la ventana
        pack();
        setTitle("Seguimiento de Vuelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establecer el tamaño máximo como el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setMaximumSize(screenSize);

        // Hacer que la ventana comience maximizada
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Utilizar el aspecto Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear el panel principal
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Crear las vistas para cada panel
        datosVueloView = new DatosVueloView(this.flightSelected);
        this.chartVelocidad = new TimeSeriesChartPanel("Tiempo-Velocidad",
                "Hora",
                "Velocidad(knots)");
        this.chartVelocidad.insertarDatos(flightSelected.getListaVelocidad());


        this.chartAltitud = new TimeSeriesChartPanel("Tiempo-Altitud",
                "Hora",
                "Altitud(ft)");
        this.chartAltitud.insertarDatos(flightSelected.getListaAltitud());

        this.imagenView = new ImagenView(mostrarImagenes.imagenfromURL(this.flightSelected.getRecentFlightsImageURL()));
        this.barraProgresion = new BarraProgresion();

        // Crear el JSplitPane principal para dividir la ventana en dos secciones verticales
        JSplitPane splitPaneVerticalIzquierda = new JSplitPane(JSplitPane.VERTICAL_SPLIT, chartVelocidad, imagenView);
        splitPaneVerticalIzquierda.setDividerLocation(300);  // Ajusta la altura del panel superior

        JScrollPane scrollPaneIzquierda = new JScrollPane(splitPaneVerticalIzquierda);


        // Crear el JSplitPane principal para dividir la ventana en dos secciones verticales
        JSplitPane splitPaneVerticalDerecha = new JSplitPane(JSplitPane.VERTICAL_SPLIT, chartAltitud, barraProgresion);
        splitPaneVerticalIzquierda.setDividerLocation(650);  // Ajusta la altura del panel superior

        // Crear el JSplitPane adicional para dividir la parte derecha en dos secciones horizontales
        JSplitPane splitPaneHorizontalDerecha = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, datosVueloView, scrollPaneIzquierda);
        splitPaneHorizontalDerecha.setDividerLocation(300);  // Ajusta la ubicación del divisor vertical

        // Crear el JSplitPane adicional para dividir la parte derecha en cuatro secciones
        JSplitPane splitPaneHorizontalDerechaTotal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneHorizontalDerecha, splitPaneVerticalDerecha);
        splitPaneHorizontalDerechaTotal.setDividerLocation(1200);  // Ajusta la ubicación del divisor vertical

        // Agregar el JSplitPane principal al cardPanel para mostrar los datos de vuelo, la gráfica y la imagen
        cardPanel.add(splitPaneHorizontalDerechaTotal, "RESULTADO");

        // Agregar el cardPanel al contenedor de la ventana
        getContentPane().add(cardPanel);

    }
    public void mostrar() {
        // Hacer visible la ventana usando SwingUtilities.invokeLater
        SwingUtilities.invokeLater(() -> {
            // Instanciar un objeto Timer para las actualizaciones periódicas
            Timer timer = new Timer(false);

            // Programar la tarea de actualización cada 10 segundos
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(() -> actualizarDatos());
                }
            }, 0, 10000);

            // Hacer visible la ventana
            setVisible(true);
        });
    }

    private void actualizarDatos() {
        flightSelected.actualizar();

        datosVueloView.actualizar(flightSelected.getSpeed(),
                flightSelected.getAltitud(),
                flightSelected.getStatus());

        chartAltitud.insertarDatos(flightSelected.getLastTimeStamp(),
                flightSelected.getAltitud());
        chartVelocidad.insertarDatos(flightSelected.getLastTimeStamp(),
                flightSelected.getSpeed());

    }
}
