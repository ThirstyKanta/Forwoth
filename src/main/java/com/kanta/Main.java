package com.kanta;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Type;

import javax.swing.*;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.kanta.components.menubar.TodoMenuBar;
import com.kanta.components.timer.SchuduleTimerManager;
import com.kanta.rowdata.file.DataFileManager;
import com.kanta.setting.LangManager;
import com.kanta.setting.SettingManager;
import com.kanta.ui.CenterP;
import com.kanta.ui.BottomControllCenter;

import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import tools.jackson.databind.JsonNode;

public class Main extends JFrame  {


    private static Main main = null;
    private JsonNode lang = LangManager.getInstance().getLang().get("Window");


   // public static LangManager langManager = new LangManager();


    public void init(){

        FlatLaf.registerCustomDefaultsSource("themes");
        System.out.println(SettingManager.getInstance().getThemeUrl());
        if (SettingManager.getInstance().getThemeUrl().equals("Dark")) {
            FlatMacDarkLaf.setup();
        }else if(SettingManager.getInstance().getThemeUrl().equals("Light")){
            FlatMacLightLaf.setup();

        }
        FlatLaf.updateUI();

        setLayout(new MigLayout("fill"));

        add(new CenterP(),"align center,w 95%!, h 85%::95%,wrap");
        add(new BottomControllCenter(),"align center");




        setTitle("Demo");
        setSize(new Dimension(900,700));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        TodoMenuBar menu = new TodoMenuBar();



        setJMenuBar(menu);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e) {



            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (!DataFileManager.getInstance().saveAll()){
                    if (getInstance() != null) {
                        JOptionPane.showConfirmDialog(getInstance(),lang.get("CLOSING_ERROR").asString(),"ERROR",JOptionPane.NO_OPTION);
                    }
                };
                SchuduleTimerManager.shutdownAll();

            }
        });



    }
    
    //パネル等の追加


    
    public Main() {
        init();
    }

    public static Main getInstance(){
        return main;
    }


    public static void main(String[] args) {
        boolean success = DataFileManager.getInstance().loadAll();



        javax.swing.SwingUtilities.invokeLater(() -> {
            main = new Main();
            DataFileManager.getInstance().warningSettingFile(success);
            main.setVisible(true);




    });
        System.out.println(SettingManager.getInstance().getLang());
    }


}