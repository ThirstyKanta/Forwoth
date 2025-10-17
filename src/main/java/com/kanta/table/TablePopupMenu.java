/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.table;

/**
 *
 * @author Kanta
 */
import java.awt.Component;

import com.kanta.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.kanta.rowdata.RowData;
import com.kanta.rowdata.RowDataManager;
import com.kanta.setting.LangManager;
import com.kanta.table.dialog.NameUpdate;

import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import tools.jackson.databind.JsonNode;


class TablePopupMenu extends JPopupMenu implements ActionListener{
        private final JsonNode lang = LangManager.getInstance().getLang().get("Menu");

        private final JMenuItem nameItem = new JMenuItem(lang.get("Update_Name").asString());
        private final JMenuItem reviewItem = new JMenuItem(lang.get("Update_Review").asString());
        private final JMenuItem removeItem = new JMenuItem(lang.get("Remove_Item").asString());
        private ArrayList<RowData> rowDatas = null;

        public void items_init(){
            nameItem.addActionListener(this);
            nameItem.setActionCommand("change_name");
            reviewItem.addActionListener(this);
            reviewItem.setActionCommand("change_review");
            removeItem.addActionListener(this);
            removeItem.setActionCommand("remove_item");
        }
        
        
        public TablePopupMenu(){
            items_init();

        

            add(nameItem);
            add(reviewItem);
            add(removeItem);
        }
        

       public void show(Component invoker, int x, int y, List<RowData> arr) {
           super.show(invoker, x, y);
           this.rowDatas = (ArrayList<RowData>) arr;
       }


        public void setRowData(ArrayList<RowData> arr) {
            this.rowDatas = arr;
        }

        public ArrayList<RowData> getRowData() {
            return rowDatas;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
          String cmd = e.getActionCommand();


            switch (cmd) {
                case "change_name" -> {
                    //Panel
                    NameUpdate nameUpdate = new NameUpdate(getRowData());
                    ModalDialog.showModal(Main.getInstance(), new SimpleModalBorder(nameUpdate, lang.get("Update_Name").asString(), SimpleModalBorder.OK_CANCEL_OPTION,
                            nameUpdate));
                }
                case "change_review" -> {
                }
                case "remove_item" -> {
                    for (RowData r : getRowData()) {
                        RowDataManager.getModel().removeRowData(r);
                    }
                }
            }
        
        }

      
        
    }