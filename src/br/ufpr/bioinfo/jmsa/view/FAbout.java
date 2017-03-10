package br.ufpr.bioinfo.jmsa.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import br.ufpr.bioinfo.jmsa.control.CConfig;
import br.ufpr.bioinfo.jmsa.view.core.SIconUtil;

public class FAbout extends JDialog
{
    private static FAbout myself;
    //
    private JLabel labelLogoUFPR = new JLabel(SIconUtil.imageIconUFPRLogo);
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JTextArea textAbout = new JTextArea();
    //
    
    public static FAbout getInstance()
    {
        if (myself == null)
        {
            myself = new FAbout();
        }
        return myself;
    }
    
    private FAbout()
    {
        super(FMainWindow.getInstance(), "About", true);
        //
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //        setUndecorated(true);
        //
        setLayout(new BorderLayout(5, 5));
        //
        add(labelLogoUFPR, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(textAbout);
        //
        labelLogoUFPR.setHorizontalAlignment(JLabel.CENTER);
        textAbout.setLineWrap(true);
        textAbout.setEditable(false);
        scrollPane.setMinimumSize(new Dimension(400, 200));
        scrollPane.setPreferredSize(scrollPane.getMinimumSize());
        //
        //
        textAbout.append(CConfig.getInstance().rb.getString("ABOUT.NAME"));
        textAbout.append(" - " + CConfig.getInstance().rb.getString("ABOUT.FULLNAME"));
        textAbout.append(" - " + CConfig.getInstance().rb.getString("ABOUT.VERSION"));
        //
        //TODO MALTON: Internacionalizar
        textAbout.append("\n" + CConfig.getInstance().rb.getString("ABOUT.NOTES"));
        //
        //
        textAbout.setCaretPosition(0);
        //
        //
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                dispose();
            }
        });
        setAlwaysOnTop(true);
        setResizable(false);
        //        
        pack();
        setLocationRelativeTo(null);
    }
}
