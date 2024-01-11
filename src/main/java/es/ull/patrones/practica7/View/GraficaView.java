package es.ull.patrones.practica7.View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

// Vista para el panel de gráfica
class GraficaView extends JPanel {
    private XYSeries altitudSeries;

    GraficaView(String nombreEjeX, String nombreEjeY, Color colorGrafica) {
        altitudSeries = new XYSeries("Altitud");

        XYSeriesCollection dataset = new XYSeriesCollection(altitudSeries);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Gráfica",
                nombreEjeX,
                nombreEjeY,
                dataset
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        plot.setDomainAxis(new NumberAxis(nombreEjeX));
        plot.setRangeAxis(new NumberAxis(nombreEjeY));

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(1));

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setTickUnit(new NumberTickUnit(1));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        chartPanel.setMouseWheelEnabled(true);

        chart.getPlot().setBackgroundPaint(colorGrafica);

        add(chartPanel);
    }
}
