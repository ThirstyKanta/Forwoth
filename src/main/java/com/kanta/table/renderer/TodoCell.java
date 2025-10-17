/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.table.renderer;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Kanta
 */
public class TodoCell extends JPanel {
    private final JLabel label;

    private final FlatSVGIcon closedFolder = new FlatSVGIcon("images/folder_close.svg");
    private final FlatSVGIcon openedFolder = new FlatSVGIcon("images/folder_open.svg");
    
    private final JLabel folderJLabel;

    public JLabel getLabel() {
        return label;
    }
  
    private void folderLabelInit(){
        closedFolder.setColorFilter(new FlatSVGIcon.ColorFilter((color) -> Color.white));
        openedFolder.setColorFilter(new FlatSVGIcon.ColorFilter((color) -> Color.white));

        label.setBackground(Color.white);
        label.setIcon(closedFolder);
        


    }

    public TodoCell(){
        
        setLayout(new MigLayout("alignx left,aligny center"));
        
        label = new JLabel();
        folderJLabel = new JLabel();
        
     
        folderLabelInit();

        add(folderJLabel);            
        add(label, "gap 0 10 0 0");    
        
    }



    public JLabel getfolderJLabel() {
        return folderJLabel;
    }
}
