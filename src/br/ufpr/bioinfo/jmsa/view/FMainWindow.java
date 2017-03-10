package br.ufpr.bioinfo.jmsa.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import br.ufpr.bioinfo.jmsa.control.CConfig;
import br.ufpr.bioinfo.jmsa.control.CControl;
import br.ufpr.bioinfo.jmsa.model.OPeaklist;
import br.ufpr.bioinfo.jmsa.model.event.useraction.OUserActionLoadPeakFiles;
import br.ufpr.bioinfo.jmsa.view.core.PPeaklistSimilarity;
import br.ufpr.bioinfo.jmsa.view.core.PSuperPeaklistPlot;
import br.ufpr.bioinfo.jmsa.view.core.PPeaklistFiles;
import br.ufpr.bioinfo.jmsa.view.core.SIconUtil;

public class FMainWindow extends JFrame
{
    private static FMainWindow myself;
    //
    public JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
    public JScrollPane scrollPeaklistTablesPlots = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    public JScrollPane scrollPeaklistTables = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    public JScrollPane scrollPeaklistPlots = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    public JTabbedPane tabbedPaneFiles = new JTabbedPane(JTabbedPane.TOP);
    public JTabbedPane tabbedPaneMain = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    public JTabbedPane tabbedPanePeaklist = new JTabbedPane(JTabbedPane.TOP);
    public JTabbedPane tabbedPaneCluster = new JTabbedPane(JTabbedPane.TOP);
    public PPeaklistFiles panelLoadingPeaklistFiles = new PPeaklistFiles("Loading");
    public JPanel panelPeaklist = new JPanel();
    public JPanel panelPeaklistTablesPlots = new JPanel();
    public JPanel panelPeaklistTables = new JPanel();
    public JPanel panelPeaklistPlots = new JPanel();
    public JPanel panelPeaklistProteins = new JPanel();
    public JPanel panelPeaklistGroups = new JPanel();
    public JPanel panelPeaklistSimilarity = new JPanel();
    public JPanel panelPeaklistInformations = new JPanel();
    public JPanel panelCluster = new JPanel();
    public JPanel panelInformation = new JPanel();
    public JPanel panelStatusBar = new JPanel();
    public PSuperPeaklistPlot superPeaklistPlot = new PSuperPeaklistPlot();
    public JMenuBar menubar = new JMenuBar();
    public JMenu menuFile = new JMenu("File");
    public JMenu menuView = new JMenu("View");
    public JMenu menuShowColumns = new JMenu("Show Columns");
    public JMenu menuPlotTitle = new JMenu("Plot Name");
    public JMenu menuHelp = new JMenu("Help");
    public JMenuItem menuItemLoadFiles = new JMenuItem("Load Files");
    public JMenuItem menuItemConfig = new JMenuItem("Configuration");
    public JMenuItem menuItemExit = new JMenuItem("Exit");
    public JMenuItem menuItemAbout = new JMenuItem("About");
    public JCheckBoxMenuItem checkBoxMenuItemShowMSName = new JCheckBoxMenuItem("Show Name", CConfig.getInstance().showMSName);
    public JCheckBoxMenuItem checkBoxMenuItemShowMSSpectrumID = new JCheckBoxMenuItem("Show SpectrumID", CConfig.getInstance().showMSSpectrumID);
    public JCheckBoxMenuItem checkBoxMenuItemShowMSSpecies = new JCheckBoxMenuItem("Show Species", CConfig.getInstance().showMSSpecies);
    public JCheckBoxMenuItem checkBoxMenuItemShowMSStrain = new JCheckBoxMenuItem("Show Strain", CConfig.getInstance().showMSStrain);
    public JCheckBoxMenuItem checkBoxMenuItemPlotTitleName = new JCheckBoxMenuItem("Name", CConfig.getInstance().plotTitleName);
    public JCheckBoxMenuItem checkBoxMenuItemPlotTitleSpectrumID = new JCheckBoxMenuItem("SpectrumID", CConfig.getInstance().plotTitleSpectrumID);
    public JCheckBoxMenuItem checkBoxMenuItemPlotTitleSpecies = new JCheckBoxMenuItem("Species", CConfig.getInstance().plotTitleSpecies);
    public JCheckBoxMenuItem checkBoxMenuItemPlotTitleStrain = new JCheckBoxMenuItem("Strain", CConfig.getInstance().plotTitleStrain);
    public JToolBar toolBar = new JToolBar("Tools");
    public JLabel labelStatusBar = new JLabel("JMSA");
    public JButton buttonLoadFiles = new JButton("Load");
    public JButton buttonPeaklist = new JButton("Peaklist");
    public JButton buttonAnalyser = new JButton("Analyser");
    public JButton buttonCluster = new JButton("Cluster");
    public JButton buttonInformation = new JButton("Information");
    
    public static FMainWindow getInstance()
    {
        if (myself == null)
        {
            myself = new FMainWindow();
        }
        return myself;
    }
    
    private FMainWindow()
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(CConfig.getInstance().rb.getString("ABOUT.FULLNAME") + " - " + CConfig.getInstance().rb.getString("ABOUT.VERSION"));
        setIconImage(SIconUtil.imageIconMainWindow32.getImage());
        //
        //
        setLayout(new BorderLayout(2, 2));
        panelPeaklist.setLayout(new BorderLayout(2, 2));
        panelPeaklistTablesPlots.setLayout(new BoxLayout(panelPeaklistTablesPlots, BoxLayout.Y_AXIS));
        panelPeaklistTables.setLayout(new BoxLayout(panelPeaklistTables, BoxLayout.Y_AXIS));
        panelPeaklistPlots.setLayout(new BoxLayout(panelPeaklistPlots, BoxLayout.Y_AXIS));
        panelPeaklistSimilarity.setLayout(new BoxLayout(panelPeaklistSimilarity, BoxLayout.Y_AXIS));
        panelPeaklistInformations.setLayout(new BoxLayout(panelPeaklistInformations, BoxLayout.Y_AXIS));
        panelCluster.setLayout(new BorderLayout(2, 2));
        panelStatusBar.setLayout(new BorderLayout(2, 2));
        //
        //
        setJMenuBar(menubar);
        menubar.add(menuFile);
        menubar.add(menuView);
        menubar.add(menuHelp);
        menuFile.add(menuItemLoadFiles);
        menuFile.addSeparator();
        menuFile.add(menuItemConfig);
        menuFile.addSeparator();
        menuFile.add(menuItemExit);
        menuView.add(menuShowColumns);
        menuView.add(menuPlotTitle);
        menuHelp.add(menuItemAbout);
        menuShowColumns.add(checkBoxMenuItemShowMSName);
        menuShowColumns.add(checkBoxMenuItemShowMSSpectrumID);
        menuShowColumns.add(checkBoxMenuItemShowMSSpecies);
        menuShowColumns.add(checkBoxMenuItemShowMSStrain);
        menuPlotTitle.add(checkBoxMenuItemPlotTitleName);
        menuPlotTitle.add(checkBoxMenuItemPlotTitleSpectrumID);
        menuPlotTitle.add(checkBoxMenuItemPlotTitleSpecies);
        menuPlotTitle.add(checkBoxMenuItemPlotTitleStrain);
        //
        add(toolBar, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(panelStatusBar, BorderLayout.SOUTH);
        toolBar.add(buttonLoadFiles);
        toolBar.add(buttonPeaklist);
        toolBar.add(buttonAnalyser);
        toolBar.add(buttonCluster);
        toolBar.add(buttonInformation);
        splitPane.add(tabbedPaneFiles);
        splitPane.add(tabbedPaneMain);
        panelStatusBar.add(labelStatusBar, BorderLayout.CENTER);
        tabbedPaneFiles.addTab("Loading", panelLoadingPeaklistFiles);
        tabbedPaneMain.addTab("Peaklist", panelPeaklist);
        tabbedPaneMain.addTab("Analyser", superPeaklistPlot);
        tabbedPaneMain.addTab("Cluster", panelCluster);
        tabbedPaneMain.addTab("Information", panelInformation);
        panelPeaklist.add(tabbedPanePeaklist, BorderLayout.CENTER);
        panelCluster.add(tabbedPaneCluster, BorderLayout.CENTER);
        tabbedPanePeaklist.addTab("Tables / Plots", scrollPeaklistTablesPlots);
        tabbedPanePeaklist.addTab("Tables", scrollPeaklistTables);
        tabbedPanePeaklist.addTab("Plots", scrollPeaklistPlots);
        tabbedPanePeaklist.addTab("Proteins", panelPeaklistProteins);
        tabbedPanePeaklist.addTab("Groups", panelPeaklistGroups);
        tabbedPanePeaklist.addTab("Similarity", panelPeaklistSimilarity);
        tabbedPanePeaklist.addTab("Informations", panelPeaklistInformations);
        scrollPeaklistTablesPlots.setViewportView(panelPeaklistTablesPlots);
        scrollPeaklistTables.setViewportView(panelPeaklistTables);
        scrollPeaklistPlots.setViewportView(panelPeaklistPlots);
        //
        //
        splitPane.setOneTouchExpandable(true);
        tabbedPanePeaklist.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPaneCluster.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        //
        //
        //
        buttonLoadFiles.setIcon(SIconUtil.imageIconLoadFiles16);
        buttonPeaklist.setIcon(SIconUtil.imageIconPeaklist16);
        buttonAnalyser.setIcon(SIconUtil.imageIconAnalyser16);
        buttonCluster.setIcon(SIconUtil.imageIconCluster16);
        buttonInformation.setIcon(SIconUtil.imageIconInformation16);
        //
        //
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent e)
            {
                super.windowClosed(e);
                CControl.getInstance().exit();
            }
        });
        tabbedPaneMain.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                superPeaklistPlot.buildPlot(panelLoadingPeaklistFiles.defaultTableModel.getSelectedPeaklists());
            }
        });
        tabbedPanePeaklist.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                List<OPeaklist> peaklists = panelLoadingPeaklistFiles.defaultTableModel.getSelectedPeaklists();
                //
                if (tabbedPanePeaklist.getSelectedComponent() == scrollPeaklistTablesPlots)
                {
                    panelPeaklistTablesPlots.removeAll();
                    for (OPeaklist peaklist : peaklists)
                    {
                        Box box = Box.createHorizontalBox();
                        box.add(peaklist.getPeaklistPlot());
                        box.add(peaklist.getPeaklistTable());
                        panelPeaklistTablesPlots.add(box);
                    }
                }
                else if (tabbedPanePeaklist.getSelectedComponent() == scrollPeaklistTables)
                {
                    panelPeaklistTables.removeAll();
                    for (OPeaklist peaklist : peaklists)
                    {
                        panelPeaklistTables.add(peaklist.getPeaklistTable());
                    }
                }
                else if (tabbedPanePeaklist.getSelectedComponent() == scrollPeaklistPlots)
                {
                    panelPeaklistPlots.removeAll();
                    for (OPeaklist peaklist : peaklists)
                    {
                        panelPeaklistPlots.add(peaklist.getPeaklistPlot());
                    }
                }
                else if (tabbedPanePeaklist.getSelectedComponent() == panelPeaklistProteins)
                {
                }
                else if (tabbedPanePeaklist.getSelectedComponent() == panelPeaklistGroups)
                {
                }
                else if (tabbedPanePeaklist.getSelectedComponent() == panelPeaklistSimilarity)
                {
                    panelPeaklistSimilarity.removeAll();
                    panelPeaklistSimilarity.add(new PPeaklistSimilarity(peaklists));
                }
                else if (tabbedPanePeaklist.getSelectedComponent() == panelPeaklistInformations)
                {
                    panelPeaklistInformations.removeAll();
                    for (OPeaklist peaklist : peaklists)
                    {
                        panelPeaklistInformations.add(peaklist.getPeaklistInfo());
                        //
                        //Break to show only the first selected
                        break;
                    }
                }
                //
                repaint();
            }
        });
        ActionListener actionListener = new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch (e.getActionCommand())
                {
                    case "ShowMSName":
                    case "ShowMSSpectrumID":
                    case "ShowMSSpecies":
                    case "ShowMSStrain":
                    case "PlotTitleName":
                    case "PlotTitleSpectrumID":
                    case "PlotTitleSpecies":
                    case "PlotTitleStrain":
                        CConfig.getInstance().showMSName = checkBoxMenuItemShowMSName.isSelected();
                        CConfig.getInstance().showMSSpectrumID = checkBoxMenuItemShowMSSpectrumID.isSelected();
                        CConfig.getInstance().showMSSpecies = checkBoxMenuItemShowMSSpecies.isSelected();
                        CConfig.getInstance().showMSStrain = checkBoxMenuItemShowMSStrain.isSelected();
                        CConfig.getInstance().plotTitleName = checkBoxMenuItemPlotTitleName.isSelected();
                        CConfig.getInstance().plotTitleSpectrumID = checkBoxMenuItemPlotTitleSpectrumID.isSelected();
                        CConfig.getInstance().plotTitleSpecies = checkBoxMenuItemPlotTitleSpecies.isSelected();
                        CConfig.getInstance().plotTitleStrain = checkBoxMenuItemPlotTitleStrain.isSelected();
                        CConfig.getInstance().saveConfig();
                        panelLoadingPeaklistFiles.setVisibleColumns(checkBoxMenuItemShowMSName.isSelected(), checkBoxMenuItemShowMSSpectrumID.isSelected(), checkBoxMenuItemShowMSSpecies.isSelected(), checkBoxMenuItemShowMSStrain.isSelected());
                        //
                        ArrayList<OPeaklist> peaklists = panelLoadingPeaklistFiles.defaultTableModel.getSelectedPeaklists();
                        for (OPeaklist peaklist : peaklists)
                        {
                            peaklist.reset();
                        }
                        superPeaklistPlot.buildPlot(peaklists);
                        //
                        int prevTab = tabbedPanePeaklist.getSelectedIndex();
                        tabbedPanePeaklist.setSelectedIndex(-1);
                        tabbedPanePeaklist.setSelectedIndex(prevTab);
                        repaint();
                        break;
                    case "loadpeakfiles":
                        JFileChooser chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new File(CConfig.getInstance().loadingPath));
                        chooser.setDialogTitle("Select Mass Spectrum/Spectra folder");
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        chooser.setMultiSelectionEnabled(true);
                        chooser.setAcceptAllFileFilterUsed(false);
                        if (chooser.showOpenDialog(FMainWindow.this) == JFileChooser.APPROVE_OPTION)
                        {
                            CControl.getInstance().threadUserActionsPool.addEvento(new OUserActionLoadPeakFiles(chooser.getSelectedFiles()));
                        }
                        break;
                    case "config":
                        new FConfig().setVisible(true);
                        break;
                    case "exit":
                        dispose();
                        break;
                    case "about":
                        FAbout.getInstance().setVisible(true);
                        break;
                    case "tab-peaklist":
                        tabbedPaneMain.setSelectedComponent(panelPeaklist);
                        break;
                    case "tab-analyser":
                        tabbedPaneMain.setSelectedComponent(superPeaklistPlot);
                        break;
                    case "tab-cluster":
                        tabbedPaneMain.setSelectedComponent(panelCluster);
                        break;
                    case "tab-information":
                        tabbedPaneMain.setSelectedComponent(panelInformation);
                        break;
                    default:
                        break;
                }
            }
        };
        checkBoxMenuItemShowMSName.setActionCommand("ShowMSName");
        checkBoxMenuItemShowMSSpectrumID.setActionCommand("ShowMSSpectrumID");
        checkBoxMenuItemShowMSSpecies.setActionCommand("ShowMSSpecies");
        checkBoxMenuItemShowMSStrain.setActionCommand("ShowMSStrain");
        //
        checkBoxMenuItemShowMSName.addActionListener(actionListener);
        checkBoxMenuItemShowMSSpectrumID.addActionListener(actionListener);
        checkBoxMenuItemShowMSSpecies.addActionListener(actionListener);
        checkBoxMenuItemShowMSStrain.addActionListener(actionListener);
        //
        checkBoxMenuItemPlotTitleName.setActionCommand("PlotTitleName");
        checkBoxMenuItemPlotTitleSpectrumID.setActionCommand("PlotTitleSpectrumID");
        checkBoxMenuItemPlotTitleSpecies.setActionCommand("PlotTitleSpecies");
        checkBoxMenuItemPlotTitleStrain.setActionCommand("PlotTitleStrain");
        //
        checkBoxMenuItemPlotTitleName.addActionListener(actionListener);
        checkBoxMenuItemPlotTitleSpectrumID.addActionListener(actionListener);
        checkBoxMenuItemPlotTitleSpecies.addActionListener(actionListener);
        checkBoxMenuItemPlotTitleStrain.addActionListener(actionListener);
        //
        menuItemLoadFiles.setActionCommand("loadpeakfiles");
        menuItemConfig.setActionCommand("config");
        menuItemExit.setActionCommand("exit");
        menuItemAbout.setActionCommand("about");
        buttonLoadFiles.setActionCommand("loadpeakfiles");
        buttonPeaklist.setActionCommand("tab-peaklist");
        buttonAnalyser.setActionCommand("tab-analyser");
        buttonCluster.setActionCommand("tab-cluster");
        buttonInformation.setActionCommand("tab-information");
        //
        menuItemLoadFiles.addActionListener(actionListener);
        menuItemConfig.addActionListener(actionListener);
        menuItemExit.addActionListener(actionListener);
        menuItemAbout.addActionListener(actionListener);
        buttonLoadFiles.addActionListener(actionListener);
        buttonPeaklist.addActionListener(actionListener);
        buttonAnalyser.addActionListener(actionListener);
        buttonCluster.addActionListener(actionListener);
        buttonInformation.addActionListener(actionListener);
        //
        //
        setPreferredSize(new Dimension(1000, 600));
        pack();
        setLocationRelativeTo(null);
        //
        //
        tabbedPanePeaklist.setSelectedIndex(0);
        //        buttonAnalyser.setEnabled(false);
        buttonCluster.setEnabled(false);
        buttonInformation.setEnabled(false);
        //        tabbedPaneMain.setEnabledAt(tabbedPaneMain.indexOfComponent(panelAnalyser), false);
        tabbedPaneMain.setEnabledAt(tabbedPaneMain.indexOfComponent(panelCluster), false);
        tabbedPaneMain.setEnabledAt(tabbedPaneMain.indexOfComponent(panelInformation), false);
        tabbedPanePeaklist.setEnabledAt(tabbedPanePeaklist.indexOfComponent(panelPeaklistProteins), false);
        tabbedPanePeaklist.setEnabledAt(tabbedPanePeaklist.indexOfComponent(panelPeaklistGroups), false);
        //        tabbedPanePeaklist.setEnabledAt(tabbedPanePeaklist.indexOfComponent(panelPeaklistSimilarity), false);
        //        tabbedPanePeaklist.setEnabledAt(tabbedPanePeaklist.indexOfComponent(panelPeaklistInformations), false);
    }
    
    public void clearTable()
    {
        panelLoadingPeaklistFiles.clearTable();
    }
    
    public void addPeaklistToTable(final OPeaklist peaklist)
    {
        panelLoadingPeaklistFiles.addPeaklistToTable(peaklist);
    }
}
