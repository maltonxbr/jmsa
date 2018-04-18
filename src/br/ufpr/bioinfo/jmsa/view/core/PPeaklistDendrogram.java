package br.ufpr.bioinfo.jmsa.view.core;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import algorithm.clustering.AverageLinkageStrategy;
import algorithm.clustering.Cluster;
import algorithm.clustering.ClusteringAlgorithm;
import algorithm.clustering.DefaultClusteringAlgorithm;
import algorithm.clustering.visualization.DendrogramPanel;
import br.ufpr.bioinfo.jmsa.analyser.CPeaklistAnalyser;
import br.ufpr.bioinfo.jmsa.model.OPeaklist;


public class PPeaklistDendrogram extends JPanel
{
	 
	    //
	 public TitledBorder titledBorder = BorderFactory.createTitledBorder("");
	
	 public JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	 
	 public JPanel content = new JPanel();
	 public PPeaklistDendrogram()
	 {
    	
        setLayout(new BorderLayout());
        
        this.setBorder(titledBorder);
       
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(content);
        this.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        
        
        this.setMinimumSize(new Dimension(300,300));
        this.setPreferredSize(new Dimension(800,600));
        validate();
        
        
    	

    }

    public void reloadDendrogram(List<OPeaklist> peaklists) {
    	this.removeAll();
    	if(peaklists.size() == 0)
    		return;
    	String[] names = new String[peaklists.size()];
        double[][] distances = new double[peaklists.size()][peaklists.size()];
        
    	for (int col = 0; col < peaklists.size(); col++)
        {
    		
    		OPeaklist peaklistCol = peaklists.get(col);
    		names[col] = peaklistCol.toString();
            for (int row = 0; row < peaklists.size(); row++)
            {
                OPeaklist peaklistRow = peaklists.get(row);
                double similarity = CPeaklistAnalyser.getPeakistSimilarity(peaklistCol, peaklistRow);
            	distances[col][row] = (1-similarity)*100;
            }
    		
        }
    	
        ClusteringAlgorithm alg = new DefaultClusteringAlgorithm();
        Cluster cluster = alg.performClustering(distances, names,
        		new AverageLinkageStrategy());
        
        DendrogramPanel dp = new DendrogramPanel();
        dp.setModel(cluster);
        
        this.add(dp);
        
    }
}
