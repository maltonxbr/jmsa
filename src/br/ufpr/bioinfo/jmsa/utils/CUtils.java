package br.ufpr.bioinfo.jmsa.utils;

import java.io.File;
import java.util.ArrayList;

public class CUtils
{
    public static ArrayList<File> listFiles(File dir, boolean recursive)
    {
        ArrayList<File> filteredFiles = new ArrayList<File>();
        File[] files = dir.listFiles();
        for (File file : files)
        {
            filteredFiles.add(file);
            if (file.isDirectory())
            {
                if (recursive)
                {
                    ArrayList<File> subdir = listFiles(file, recursive);
                    for (File subdirfile : subdir)
                    {
                        filteredFiles.add(subdirfile);
                    }
                }
            }
        }
        return filteredFiles;
    }
    public static void readXML()
    {
        
    }
}
