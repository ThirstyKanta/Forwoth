package com.kanta.rowdata.file;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;

public class JsonFileFilter extends javax.swing.filechooser.FileFilter implements FileFilter {


    @Override
    public boolean accept(File f) {


        if (!f.getName().contains(":")) {
            if (FilenameUtils.getExtension(f.getName()).equals("json")) {
                return true;
            }
        }

        return f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "JSON FILE";
    }

}
