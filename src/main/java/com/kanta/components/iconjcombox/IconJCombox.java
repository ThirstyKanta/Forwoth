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

    String[] icons = {
            "bullhorn-solid-full.svg",
            "calculator-solid-full.svg",
            "ellipsis.svg",
            "folder_close.svg",
            "folder_open.svg",
            "font-awesome-solid-full.svg",
            "house-solid-full.svg",
            "key-solid-full.svg",
            "panorama-solid-full.svg",
            "person-falling-burst-solid-full.svg",
            "scroll-solid-full.svg",
            "table-list-solid-full.svg",
            "wifi-solid-full.svg"
    };

    private DefaultComboBoxModel model = new DefaultComboBoxModel();



    public IconJCombox(){


        setModel(model);
        setPreferredSize(new Dimension(100,50));
        for (String icons : icons) {
            FlatSVGIcon icon = new FlatSVGIcon("images/selectable_icons/" + icons, 20, 20);
            model.addElement(icon);
        }


    }


}
