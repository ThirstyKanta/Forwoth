/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.table.dialog;


import java.awt.Dialog;
import java.awt.Dimension;
import java.lang.annotation.Target;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

import com.kanta.rowdata.RowData;



/**
 *
 * @author Kanta
 */


public class DialogBase extends JPanel{
    protected List<RowData> targetRowData_arr;

    private int dialog_width = 0;
    private int dialog_height = 0;
    public DialogBase(List<RowData> targetRowData_arr){
        this.targetRowData_arr = targetRowData_arr;
    }
    public void setOptionalDialogSize(int dialog_width, int dialog_height){
        this.dialog_height = dialog_height;
        this.dialog_width = dialog_width;
    }

    public int getDialog_height() {
        return dialog_height;
    }
    public int getDialog_width() {
        return dialog_width;
    }

}
