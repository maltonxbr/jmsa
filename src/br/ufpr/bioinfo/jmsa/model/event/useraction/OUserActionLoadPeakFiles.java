package br.ufpr.bioinfo.jmsa.model.event.useraction;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import br.ufpr.bioinfo.jmsa.model.OPeaklist;
import br.ufpr.bioinfo.jmsa.utils.CUtils;
import br.ufpr.bioinfo.jmsa.view.FMainWindow;

public class OUserActionLoadPeakFiles implements OEvento
{
    public File[] seletedFolders;
    
    public OUserActionLoadPeakFiles(File[] seletedFolders)
    {
        this.seletedFolders = seletedFolders;
    }
    
    @Override
    public synchronized void executarEvento()
    {
        //TODO MALTON: Teste de Tempo
        long start = System.currentTimeMillis();
        //        System.out.println("Iniciando o Loading: " + start);
        //
        //
        //TODO MALTON: N�o utilizar JOptionPane pois pode causar uma inconsist�ncia. Criar um JDialog personalizado
        Object[] options = { "Cancel" };
        final JOptionPane pane = new JOptionPane("Loading Peaklists", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options);
        final JProgressBar progressBar = new JProgressBar(0, 0);
        final JLabel lblStatusBar = new JLabel("Loading Peaklists");
        final JDialog dialog = pane.createDialog("Loading Peaklists");
        progressBar.setStringPainted(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.add(BorderLayout.NORTH, progressBar);
        dialog.add(BorderLayout.CENTER, lblStatusBar);
        dialog.add(BorderLayout.SOUTH, pane);
        dialog.setAlwaysOnTop(true);
        dialog.pack();
        //
        //
        //
        //
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                dialog.setVisible(true);
            }
        });
        //
        try
        {
            if (seletedFolders.length > 0)
            {
                progressBar.setIndeterminate(true);
                progressBar.setValue(0);
                lblStatusBar.setText("Progress...");
                FMainWindow.getInstance().clearTable();
                //
                //
                ArrayList<File> foundPeakFiles = new ArrayList<File>();
                for (File folder : seletedFolders)
                {
                    ArrayList<File> files = CUtils.listFiles(folder, true);
                    for (File file : files)
                    {
                        if (!file.isDirectory())
                        {
                            if (file.getName().endsWith("xml") || file.getName().toLowerCase().endsWith("peaks"))
                            {
                                foundPeakFiles.add(file);
                            }
                        }
                    }
                }
                //
                //
                progressBar.setIndeterminate(false);
                progressBar.setMaximum(foundPeakFiles.size());
                //
                //
                int validsCounter = 0;
                int invalidsCounter = 0;
                ArrayList<OPeaklist> listPeaklist = new ArrayList<OPeaklist>(); //TODO MALTON: ArrayList deveria ser um Treemap ou Hashmap
                for (File path : foundPeakFiles)
                {
                    if (pane.getValue() != JOptionPane.UNINITIALIZED_VALUE)
                    {
                        throw new Exception("Canceled");
                    }
                    try
                    {
                        OPeaklist peaklist = new OPeaklist(path);
                        //
                        if (peaklist.valid)
                        {
                            listPeaklist.add(peaklist);
                            FMainWindow.getInstance().addPeaklistToTable(peaklist);
                            validsCounter++;
                        }
                        else
                        {
                            invalidsCounter++;
                        }
                        //
                        //
                        progressBar.setValue(validsCounter + invalidsCounter);
                        lblStatusBar.setText(validsCounter + " valids. " + invalidsCounter + " invalids. " + foundPeakFiles.size() + " total");
                    }
                    catch (ParserConfigurationException | SAXException | IOException e)
                    {
                        e.printStackTrace();
                        invalidsCounter++;
                    }
                }
            }
        }
        catch (InterruptedException e1)
        {
            e1.printStackTrace();
        }
        catch (Exception e1)
        {
            progressBar.setIndeterminate(true);
            progressBar.setMinimum(0);
            progressBar.setMaximum(0);
            progressBar.setValue(0);
            lblStatusBar.setText("Progress...");
            FMainWindow.getInstance().clearTable();
        }
        finally
        {
            dialog.dispose();
        }
        System.out.println("Finalizando o Loading: " + (System.currentTimeMillis() - start));
    }
}
