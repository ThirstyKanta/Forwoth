/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.kanta.rowdata.RowData;
import com.kanta.rowdata.RowDataManager;
import com.kanta.setting.LangManager;
import com.kanta.table.dialog.TodoContent;
import tools.jackson.databind.JsonNode;

/**
 *
 * @author Kanta
 */
public class TodoTableModel extends AbstractTableModel{
    
    private final JsonNode lang =  LangManager.getInstance().getLang().get("Column_Name");
    private final String[] colName = {lang.get("todo").asString(),lang.get("review").asString()};
    private final Class[] colClass = {JLabel.class,JLabel.class};
    private ArrayList<RowData> rowList = new ArrayList<>();



    public ArrayList<RowData> getRowList() {
        return rowList;
    }


    public void addRowData(RowData rowData){
        rowList.add(rowData);

        fireTableRowsInserted(0,rowList.size());
    }
    public void removeRowData(RowData rowData){
        rowList.remove(rowData);

        fireTableRowsDeleted(0,rowList.size());
    }

    public void setRowList(ArrayList<RowData> rowList) {
        this.rowList = rowList;
        fireTableDataChanged();

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        return  colClass[columnIndex];
    }
   
    @Override
    public String getColumnName(int column) {
        return colName[column];
    }

    @Override
    public int getRowCount() {
        return rowList.size();
    }

    @Override
    public int getColumnCount() {
        return colName.length;
    }
    public RowData getRowData(int row){
        return rowList.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

         if (columnIndex == 0){
            return getRowData(rowIndex).getName();
        }else if(columnIndex == 1){
            return getRowData(rowIndex).getInterval();
        }
        return 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        RowData rowData = rowList.get(rowIndex);
        // System.out.println("hello");
        if (columnIndex == 0 && aValue instanceof String) {
            rowData.setName((String) aValue);
        }  //rowData.setInterval((Integer) aValue);

    }
}
