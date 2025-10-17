/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.table.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatLaf;
import com.kanta.rowdata.RowData;
import com.kanta.rowdata.RowDataManager;
import com.kanta.setting.SettingManager;

/**
 *
 * @author Kanta
 */
@SuppressWarnings("ALL")
public class TodoTableRenderer extends DefaultTableCellRenderer {
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
            
            setHorizontalAlignment(JLabel.LEFT);
            setForeground(UIManager.getColor("foreground"));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
         
          if (column == 1){
                setHorizontalAlignment(JLabel.CENTER);                 
            if(value instanceof Integer && SettingManager.getInstance().getBeforeDays() >= (Integer)value){
                setForeground(Color.RED);
            }else {
            }
        }

        return c;
    }

}
