package es.ull.patrones.practica7;

import org.apache.commons.lang3.tuple.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
//import org.jfree.chart.ui.ChartPanel;
//import org.jfree.chart.
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeSeriesChart extends ApplicationFrame {


    private TimeSeries timeSeries;
    private TimeSeriesCollection dataset;
    private JFreeChart chart;

    public TimeSeriesChart(String title, String xAxisLabel, String yAxisLabel) {
        super(title);  // Llama al constructor de ApplicationFrame con el título
        // Crear una serie temporal
        timeSeries = new TimeSeries(title);

        // Crear un conjunto de datos con la serie temporal
        dataset = new TimeSeriesCollection(timeSeries);

        // Crear el gráfico de series temporales
        chart = ChartFactory.createTimeSeriesChart(
                title,
                xAxisLabel,
                yAxisLabel,
                dataset,
                false,
                true,
                false
        );

        // Personalizar el eje X para mostrar la hora en un formato legible
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
    }

    public void insertarDatos(Long unixDate, Integer value) {
        timeSeries.addOrUpdate(new Second(new Date(unixDate * 1000L)), value);
    }

    public void insertarDatos(List<Pair<Long,Integer>> valueList) {
        for (Pair<Long,Integer> reg : valueList){
            insertarDatos(reg.getLeft(),reg.getRight());
        }
    }

    public void mostrar() {
        SwingUtilities.invokeLater(() -> {
            // Crear el panel de gráficos
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 600));

            // Crear la ventana
            JFrame frame = new JFrame("Gráfico de Series Temporales");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(chartPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
