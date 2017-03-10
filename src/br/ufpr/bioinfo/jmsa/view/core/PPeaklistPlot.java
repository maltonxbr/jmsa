package br.ufpr.bioinfo.jmsa.view.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYIntervalSeries;
import org.jfree.data.xy.XYIntervalSeriesCollection;
import br.ufpr.bioinfo.jmsa.model.OPeak;
import br.ufpr.bioinfo.jmsa.model.OPeaklist;

public class PPeaklistPlot extends JPanel
{
    public OPeaklist peaklist;
    //
    public TitledBorder titledBorder = BorderFactory.createTitledBorder("");
    
    public PPeaklistPlot(OPeaklist peaklist)
    {
        this.peaklist = peaklist;
        //
        //
        setLayout(new BorderLayout());
        //        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //
        setBorder(titledBorder);
        setAlignmentX(JPanel.LEFT_ALIGNMENT);
        //
        //
        XYIntervalSeriesCollection dataset = new XYIntervalSeriesCollection();
        XYIntervalSeries intervalSeries = new XYIntervalSeries(peaklist.toString());
        JFreeChart jFreeChart = ChartFactory.createXYBarChart(peaklist.toString(), "MASS", false, "INTENSITY", dataset, PlotOrientation.VERTICAL, false, true, true);
        XYPlot xyPlot = jFreeChart.getXYPlot();
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        
        for (Iterator<OPeak> iterator = peaklist.peaks.iterator(); iterator.hasNext();)
        {
            OPeak peak = iterator.next();
            intervalSeries.add(peak.absi, peak.absi, peak.absi, peak.mass, peak.mass, peak.mass);
        }
        dataset.addSeries(intervalSeries);
        //
        xyPlot.getRenderer().setSeriesPaint(0,peaklist.spectrumForegroundColor);
        add(chartPanel, BorderLayout.CENTER);
        //
        //
        titledBorder.setTitle(peaklist.toString());
        titledBorder.setTitleColor(peaklist.spectrumForegroundColor);
        titledBorder.setBorder(BorderFactory.createLineBorder(peaklist.spectrumForegroundColor, 3));
        //
        //
        chartPanel.setMinimumSize(new Dimension(300, 100));
        chartPanel.setPreferredSize(getMinimumSize());
        setMinimumSize(new Dimension(30, 30));
        setPreferredSize(null);
        validate();
    }
}
