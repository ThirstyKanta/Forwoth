package com.kanta.ui;

import com.kanta.rowdata.RowData;
import com.kanta.rowdata.RowDataManager;
import com.kanta.table.TodoTable;
import com.kanta.table.TodoTableModel;
import com.kanta.table.renderer.TodoCellRenderer;
import com.kanta.table.renderer.TodoTableRenderer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class BrowserPanel extends JPanel {
    private final TodoTableModel model = new TodoTableModel();
    private JTable table = new JTable();
    public BrowserPanel(){
        setLayout(new MigLayout("fill"));
        table.setModel(model);
        table.setDefaultRenderer(Object.class,new TodoTableRenderer());
        //table.getColumnModel().getColumn(0).setCellRenderer(new TodoCellRenderer());
        for (RowData data : RowDataManager.getModel().getRowList()){
            model.addRowData(data);
        }
        for (RowData data :RowDataManager.getInstance().getWaitRowDataList()){
            model.addRowData(data);
        }

       // table.getColumnModel().getColumn(1).setCellRenderer(new TodoCellRenderer());
        JScrollPane jScrollPane = new JScrollPane(table);

        for (RowData rowData:model.getRowList()){
            System.out.println(rowData.getName());
        }


        add(jScrollPane);

    }


}
