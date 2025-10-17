/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.ui;

import java.awt.Color;

import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;
import com.kanta.table.TodoTable;

import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Kanta
 */
public class CenterP extends JPanel  {

    private final TodoTable table = new TodoTable();


    public CenterP(){
         //design
        setLayout(new MigLayout("fill"));
        setBackground(Color.white);
        putClientProperty(FlatClientProperties.STYLE,
        "arc:50;"+ 
        "[dark]background: shade(@background,15%);" +
        "[dark]border: 15,15,15,15,tint(@background,10%),,50");
        //table
       // table = new TodoTable();
        
        //add element
        add(table,"grow,wrap, h 80%!");


    }


}
