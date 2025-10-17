package com.kanta.components.menubar;

import com.kanta.Main;
import com.kanta.rowdata.file.DataFileManager;
import com.kanta.setting.LangManager;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import tools.jackson.databind.JsonNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoMenuBar extends JMenuBar implements ActionListener {
    private final JsonNode lang = LangManager.getInstance().getLang().get("toolbar");

    public TodoMenuBar(){
        JMenu tool = new JMenu("Tools");
        JMenu file = new JMenu("File");

        JMenuItem export = new JMenuItem(lang.get("export").asString());
        JMenuItem save = new JMenuItem(lang.get("save").asString());

        JMenuItem setting = new JMenuItem("Setting");


        file.add(save);
        file.add(export);

        setting.addActionListener(this);
        setting.setActionCommand("setting");

        save.addActionListener(this);
        save.setActionCommand("save");
        export.addActionListener(this);
        export.setActionCommand("export");
        tool.add(setting);

        add(file);
        add(tool);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd =e.getActionCommand();

        if (cmd.equals("setting")){
            SettingP settingP = new SettingP();
            ModalDialog.showModal(Main.getInstance(),new SimpleModalBorder(settingP,"SETTING",SimpleModalBorder.OK_CANCEL_OPTION,settingP));
        }else if (cmd.equals("export")){
            DataFileManager.getInstance().export();

        }else if (cmd.equals("save")){
            DataFileManager.getInstance().saveAll();
        }
    }
}
