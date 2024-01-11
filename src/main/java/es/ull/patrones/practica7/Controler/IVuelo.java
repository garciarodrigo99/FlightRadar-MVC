package es.ull.patrones.practica7.Controler;
import es.ull.patrones.practica7.TimeSeriesChartPanel;
import es.ull.patrones.practica7.View.BarraProgresion;
import es.ull.patrones.practica7.View.DatosVueloView;
import es.ull.patrones.practica7.View.DescargarYMostrarImagen;
import es.ull.patrones.practica7.View.ImagenView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IVuelo extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JComboBox<String> opcionesComboBox;
    private JTextField cuadroTexto;
    private JButton botonAccion;
    private DatosVueloView datosVueloView;

    private ImagenView imagenView;
    private BarraProgresion barraProgresion;

    public IVuelo() {
        // Configuración de la ventana
        setPreferredSize(new Dimension(400, 300));  // Tamaño inicial
        pack();
        setTitle("Seguimiento de Vuelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Utilizar el aspecto Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear el panel principal
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Crear el panel de entrada
        JPanel entradaPanel = new JPanel(new GridLayout(4, 1));

        // Crear el JComboBox con las opciones
        String[] opciones = {"Matrícula", "ID", "Vuelo"};
        opcionesComboBox = new JComboBox<>(opciones);

        // Crear el JTextField
        cuadroTexto = new JTextField();

        // Crear el JButton para realizar alguna acción
        botonAccion = new JButton("Continuar");

        // Personalizar el botón
        botonAccion.setBackground(new Color(50, 205, 50)); // Verde
        botonAccion.setForeground(Color.WHITE);
        botonAccion.setFocusPainted(false);

        // Configurar el ActionListener para el botón
        botonAccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) opcionesComboBox.getSelectedItem();
                String textoIngresado = cuadroTexto.getText();

                // Lógica para mostrar los datos de vuelo, la gráfica y la imagen
                // Puedes agregar aquí la lógica para cargar los datos, la gráfica y la imagen.

                // Cambiar a pantalla completa después de hacer clic en "Continuar"
                setExtendedState(JFrame.MAXIMIZED_BOTH);

                // Mostrar el resultado y ocultar los demás componentes
                cardLayout.show(cardPanel, "RESULTADO");
            }
        });

        // Agregar componentes al panel de entrada
        entradaPanel.add(opcionesComboBox);
        entradaPanel.add(cuadroTexto);
        entradaPanel.add(botonAccion);

        // Crear las vistas para cada panel
        datosVueloView = new DatosVueloView();
        TimeSeriesChartPanel chartVelocidad = new TimeSeriesChartPanel("Ejemplo de Gráfico de Hora de Unix",
                "Hora",
                "Velocidad");
        chartVelocidad.insertarDatos(1704911708, 10);
        chartVelocidad.insertarDatos(1704911641, 20);
        chartVelocidad.insertarDatos(1704981775, 30);

        TimeSeriesChartPanel chartAltitud = new TimeSeriesChartPanel("Ejemplo de Gráfico de Hora de Unix",
                "Hora",
                "Velocidad");
        chartAltitud.insertarDatos(1704911708, 10);
        chartAltitud.insertarDatos(1704911641, 20);
        chartAltitud.insertarDatos(1704981775, 30);
        //chart.mostrar();
        //graficaViewAltitud = new GraficaView("Tiempo", "Altitud", Color.CYAN);
        //graficaViewVelocidad = new GraficaView("Tiempo", "Velocidad", Color.YELLOW);
        imagenView = new ImagenView(DescargarYMostrarImagen.imagenfromURL("http://www.gcmap.com/map?P=c:%23ce0c87,TFN-LPA/MAD/LHR/JFK,MAD-BCN&MS=wls2&MR=540&MX=720x360&PM=*"));
        barraProgresion = new BarraProgresion();

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

        // Agregar el panel de entrada al cardPanel
        cardPanel.add(entradaPanel, "ENTRADA");

        // Agregar el JSplitPane principal al cardPanel para mostrar los datos de vuelo, la gráfica y la imagen
        cardPanel.add(splitPaneHorizontalDerechaTotal, "RESULTADO");

        // Agregar el cardPanel al contenedor de la ventana
        getContentPane().add(cardPanel);

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IVuelo());
    }
}
