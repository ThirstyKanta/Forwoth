/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.table.dialog;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;
import com.kanta.rowdata.RowData;

import com.kanta.rowdata.RowDataManager;
import net.miginfocom.swing.MigLayout;
import raven.modal.component.SimpleModalBorder;
import raven.modal.listener.ModalCallback;
import raven.modal.listener.ModalController;

/**
 *
 * @author Kanta
 */
public class NameUpdate extends DialogBase implements ModalCallback{
    private final JTextField textField;
    
    public NameUpdate(List<RowData> targetrow_arr){
        super(targetrow_arr);
        int width = 500;
        int height = 50;
        
        setPreferredSize(new Dimension(width,height));
        textField = new JTextField();
        textField.setText(targetrow_arr.get(0).getName());
        textField.putClientProperty(FlatClientProperties.STYLE,"arc:8");
        setOptionalDialogSize(500, 150);
        setLayout(new MigLayout("fillx"));           
        add(textField,"span,grow");
    }


    @Override
    public void action(ModalController controller, int action) {
        if (action == SimpleModalBorder.OK_OPTION){
           if (!textField.getText().trim().isEmpty()){
               targetRowData_arr.get(0).setName(textField.getText());
               RowDataManager.getInstance().getDuplicateCheck(true);

            }
        }
    }

    
    

}
