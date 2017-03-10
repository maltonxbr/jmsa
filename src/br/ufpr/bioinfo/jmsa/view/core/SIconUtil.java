package br.ufpr.bioinfo.jmsa.view.core;

import javax.swing.ImageIcon;
import br.ufpr.bioinfo.jmsa.control.CConfig;

public class SIconUtil
{
    public static ImageIcon imageIconMainWindow32 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "mainwindow32.png"));
    public static ImageIcon imageIconConfig32 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "config32.png"));
    public static ImageIcon imageIconUFPRLogo = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "logo_ufpr.jpg"));
    
    public static ImageIcon imageIconOk16 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "ok16.png"));
    public static ImageIcon imageIconCancel16 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "cancel16.png"));
    
    public static ImageIcon imageIconLoadingPath16 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "folder16.png"));
    public static ImageIcon imageIconLoadFiles16 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "folder16.png"));

    public static ImageIcon imageIconPeaklist16 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "peaklist16.png"));
    public static ImageIcon imageIconAnalyser16 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "analyser16.png"));
    public static ImageIcon imageIconCluster16 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "cluster16.png"));
    public static ImageIcon imageIconInformation16 = new ImageIcon(SIconUtil.class.getResource(CConfig.getInstance().rb.getString("PATH.IMAGES") + "information16.png"));
}
