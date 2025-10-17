package com.kanta.table.components.folderbutton;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellIconLabel extends JLabel {

    private FlatSVGIcon icon;
    private Color color;
    Color table_color = UIManager.getColor("Table.background");
    private boolean isVisible = true;


    public void setVisible(boolean visible){
       this.isVisible = visible;
    }






    public CellIconLabel(String icon_path,Color selected_color) {
            FlatSVGIcon svg = new FlatSVGIcon(icon_path,20,20);
            svg.setColorFilter(new FlatSVGIcon.ColorFilter(color -> selected_color));
        if (isVisible) {
            setIcon(svg);
        }else {
            setIcon(null);
        }

    }



}
