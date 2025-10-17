package com.kanta.components.iconjcombox;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;

@SuppressWarnings("ALL")
public class IconJCombox extends JComboBox {

    URL imagesUrl = getClass().getResource("/images/selectable_icons/");
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    File[] images = new File(imagesUrl.getPath()).listFiles((dir, name) -> {
        if (FilenameUtils.getExtension(name).equals("svg")){
            return true;
    }
    return false;
    });
    public IconJCombox(){
        setModel(model);


        setPreferredSize(new Dimension(100,50));
        for (File image : images) {
            FlatSVGIcon icon = new FlatSVGIcon("images/selectable_icons/" + image.getName(), 20, 20);
            model.addElement(icon);
        }


    }


}
