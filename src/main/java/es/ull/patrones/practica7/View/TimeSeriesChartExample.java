package es.ull.patrones.practica7.View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.ApplicationFrame;

import org.jfree.ui.UIUtilities;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSeriesChartExample {

    /*public TimeSeriesChartExample(String title) {
        super(title);

        // Crear una serie temporal
        TimeSeries timeSeries = new TimeSeries("Hora de Unix");

        // Añadir datos de ejemplo (hora de Unix y un valor)
        timeSeries.addOrUpdate(new Second(new Date(1704911708 * 1000L)), 10);
        timeSeries.addOrUpdate(new Second(new Date(1704911641 * 1000L)), 20);
        // Añadir más datos según sea necesario

        // Crear un conjunto de datos con la serie temporal
        TimeSeriesCollection dataset = new TimeSeriesCollection(timeSeries);

        // Crear el gráfico de series temporales
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Gráfico de Hora de Unix",
                "Tiempo",
                "Valor",
                dataset,
                false,
                true,
                false
        );

        // Personalizar el eje X para mostrar la hora en un formato legible
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));

        // Ajustar la unidad de las marcas de tiempo en el eje X
        dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.SECOND, 10));

        // Crear el panel de gráficos y agregarlo al marco de la aplicación
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimeSeriesChartExample example = new TimeSeriesChartExample("Ejemplo de Gráfico de Hora de Unix");
            example.pack();
            UIUtilities.centerFrameOnScreen(example);
            example.setVisible(true);
        });
    }*/
}

