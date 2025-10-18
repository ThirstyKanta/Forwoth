/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.table;



import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.formdev.flatlaf.FlatClientProperties;
import com.kanta.Main;
import com.kanta.components.timer.SchuduleTimerManager;
import com.kanta.rowdata.RowData;
import com.kanta.rowdata.RowDataManager;
import com.kanta.setting.LangManager;
import com.kanta.setting.SettingManager;
import com.kanta.table.dialog.ContentPanel;
import com.kanta.table.renderer.TodoCellRenderer;
import com.kanta.table.renderer.TodoTableRenderer;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import tools.jackson.databind.JsonNode;


/**
 *
 * @author Kanta
 */
public class TodoTable extends JScrollPane implements MouseListener {
    private TablePopupMenu popupMenu;
    //private TodoTableModel tableModel = null;
    private JTable todoJTable;
    private int beforeDays = 2;

    private void ui_init() {

        putClientProperty(FlatClientProperties.STYLE, "[dark]background:shade(@background,15%);");

    }

    public void tableCheck(){
        RowData[] wait_rowDatas = new RowData[RowDataManager.getInstance().getWaitRowDataList().size()];
        RowData[] rowDatas = new RowData[RowDataManager.getModel().getRowList().size()];

        RowDataManager.getModel().getRowList().toArray(rowDatas);
        RowDataManager.getInstance().getWaitRowDataList().toArray(wait_rowDatas);
        beforeDays = SettingManager.getInstance().getBeforeDays();


        for (RowData r: wait_rowDatas){
            if (r.getInterval() <= beforeDays) {
                RowDataManager.getInstance().moveToRowData(r);
            }
        }
        for (RowData r: rowDatas){
            if (r.getInterval() > beforeDays) {
                RowDataManager.getInstance().moveToFinishData(r);
            }
        }
    }

    private void table_init() {
        if (todoJTable != null) {
            //ui
            todoJTable.putClientProperty(FlatClientProperties.STYLE, "[dark]background:shade(@background,15%);");
            todoJTable.getTableHeader().setReorderingAllowed(false);
            todoJTable.getColumnModel().getColumn(0).setPreferredWidth(200);

            todoJTable.setDefaultRenderer(Object.class, new TodoTableRenderer());
            todoJTable.addMouseListener(this);

            todoJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);


            todoJTable.getColumnModel().getColumn(0).setCellRenderer(new TodoCellRenderer());
            todoJTable.getColumnModel().getColumn(1).setCellRenderer(new TodoCellRenderer());

            ScheduledExecutorService executorService =  Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    RowData[] wait_rowDatas = new RowData[RowDataManager.getInstance().getWaitRowDataList().size()];
                    RowData[] rowDatas = new RowData[RowDataManager.getModel().getRowList().size()];

                    RowDataManager.getModel().getRowList().toArray(rowDatas);
                    RowDataManager.getInstance().getWaitRowDataList().toArray(wait_rowDatas);
                    beforeDays = SettingManager.getInstance().getBeforeDays();


                    for (RowData r: wait_rowDatas){
                        if (r.getInterval() <= beforeDays) {
                            RowDataManager.getInstance().moveToRowData(r);
                        }
                    }
                    for (RowData r: rowDatas){
                        if (r.getInterval() > beforeDays) {
                            RowDataManager.getInstance().moveToFinishData(r);
                        }
                    }

                }
            },1,2, TimeUnit.SECONDS);
            SchuduleTimerManager.register(executorService);


        }

    }


    public TodoTable() {



        //RowDataManager.getInstance().addListener(tableModel::fireTableDataChanged);

        todoJTable = new JTable(RowDataManager.getModel());
        popupMenu = new TablePopupMenu();


        ui_init();
        table_init();

        //insert sample data
        //for (RowData rowData : rowDataManager.getRowArrayList()) {tableModel.addRow(rowData.getRowDataObjects());}


        setViewportView(todoJTable);

    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == e.BUTTON2){
            RowDataManager.getInstance().getWaitRowDataList().get(0).setNextSchedule(LocalDate.now());
        }
        if (e.getButton() == e.BUTTON3 && todoJTable.getSelectedRowCount() > 0) {
            List<RowData> arr = new ArrayList<>();
            for(int row = todoJTable.getSelectedRow(); row < (todoJTable.getSelectedRow() + todoJTable.getSelectedRowCount()); row++) {
                arr.add(RowDataManager.getModel().getRowData(row));

                System.out.println(row);

            }
            System.out.printf("selectedRowCount = %d" +
                    "\nSelectRow = %d%n",todoJTable.getSelectedRowCount(),todoJTable.getSelectedRow());

            // System.out.println(arr.size());




            popupMenu.show(e.getComponent(), e.getX(), e.getY(), arr);

        }
    }

    private final JsonNode lang = LangManager.getInstance().getLang().get("Modal_dialog");

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() >= 2 && e.getButton() == e.BUTTON1) {
            ArrayList<RowData> arr = new ArrayList<>();
            for(int row = todoJTable.getSelectedRow(); row < (todoJTable.getSelectedRow() + todoJTable.getSelectedRowCount()); row++) {
                arr.add(RowDataManager.getModel().getRowData(row));
            }
            ContentPanel contentPanel = new ContentPanel(arr);

            ModalDialog.showModal(Main.getInstance(), new SimpleModalBorder(contentPanel,
                    "Content",
                    SimpleModalBorder.YES_NO_OPTION,
                    contentPanel),contentPanel.getOptions());

        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
