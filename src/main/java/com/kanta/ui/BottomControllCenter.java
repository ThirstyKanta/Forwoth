/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.kanta.Main;
import com.kanta.rowdata.file.DataFileManager;
import com.kanta.setting.LangManager;
import com.kanta.table.utils.ColorManager;
import com.kanta.todoDialog.AddToDo;

import net.miginfocom.swing.MigLayout;
import org.apache.commons.io.FilenameUtils;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import tools.jackson.databind.JsonNode;

/**
 *
 * @author Kanta
 */
public class BottomControllCenter extends JPanel implements ActionListener{
    private final JsonNode lang = LangManager.getInstance().getLang().get("ControllCenter");
    private final JButton addRowData;
    private final JButton importRowData;
    private final JButton openBrowser;
    private final JFileChooser fileChooser = new JFileChooser();

    public BottomControllCenter(){
        setLayout(new MigLayout("fillx","[][][]","[][][]"));
        addRowData = new JButton(lang.get("Add_RowData").asString());
        importRowData = new JButton(lang.get("Import_RowData").asString());
        openBrowser = new JButton("");
        addRowData.addActionListener(this);
        addRowData.setActionCommand("add");

        importRowData.addActionListener(this);
        importRowData.setActionCommand("import");

        openBrowser.addActionListener(this);
        FlatSVGIcon icon = new FlatSVGIcon("image/icons/calender.svg",20,20);
        openBrowser.setIcon(icon);
        openBrowser.setContentAreaFilled(false);
        openBrowser.setActionCommand("openbrowser");

        icon.setColorFilter(new FlatSVGIcon.ColorFilter((color) -> ColorManager.foreground.darker()));
        openBrowser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                icon.setColorFilter(new FlatSVGIcon.ColorFilter((color) -> ColorManager.foreground.darker()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                icon.setColorFilter(new FlatSVGIcon.ColorFilter((color) -> ColorManager.foreground));
            }
        });




        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                String filename = f.getName().contains(":") ? null : f.getName();
                if (filename != null) {
                    return FilenameUtils.getExtension(filename).equals("json") || f.isDirectory();
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "json file";
            }
        });



        add(addRowData);
        add(importRowData);
        add(openBrowser);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
       
        if (cmd.equals("add")){
            AddToDo add_ToDo = new AddToDo();
            ModalDialog.showModal(Main.getInstance(),new SimpleModalBorder(add_ToDo,"Add ToDo",SimpleModalBorder.OK_CANCEL_OPTION,add_ToDo));
           
        }else if(cmd.equals("import")){

            int selceted = fileChooser.showOpenDialog(this);
            if (selceted == JFileChooser.APPROVE_OPTION){
                DataFileManager.getInstance().loadSaveData(fileChooser.getSelectedFile());
            }

        }else if (cmd.equals("openbrowser")){
            System.out.println("HELLO");



            ModalDialog.showModal(Main.getInstance(),new SimpleModalBorder(new BrowserPanel(),"BROWSER",SimpleModalBorder.OK_CANCEL_OPTION,null));
        }

    }


    



}
