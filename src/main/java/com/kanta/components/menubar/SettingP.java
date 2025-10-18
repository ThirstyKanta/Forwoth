package com.kanta.components.menubar;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.kanta.components.iconjcombox.IconJCombox;
import com.kanta.rowdata.file.DataFileManager;
import com.kanta.setting.LangManager;
import com.kanta.setting.SettingManager;
import net.miginfocom.swing.MigLayout;
import raven.modal.component.SimpleModalBorder;
import raven.modal.listener.ModalCallback;
import raven.modal.listener.ModalController;
import tools.jackson.databind.JsonNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

@SuppressWarnings("ALL")
public class SettingP extends JPanel implements ModalCallback {
    private JsonNode lang = LangManager.getInstance().getLang().get("Setting");
    private final JFileChooser chooser = new JFileChooser();
    private final JTextField fileUrl = new JTextField();
    private final JButton openBtn = new JButton("Open");

    //theme demo
    private final DefaultComboBoxModel themeModel = new DefaultComboBoxModel();
    private final JComboBox themeBox = new JComboBox();
    //theme demo
    private final DefaultComboBoxModel langBoxModel =new DefaultComboBoxModel();
    private final JComboBox langBox = new JComboBox();

    private String langSetting = SettingManager.getInstance().getLang();
    private String langFiles[] = {
      "en_us.json","ja_jp.json"
    };

    private JLabel restart = new JLabel(lang.get("Restart").asString());

    //demo

    private JLabel displayDays = new JLabel(lang.get("DisplayDays").asString());
    private int days = SettingManager.getInstance().getBeforeDays();
    private DefaultComboBoxModel daysModel = new DefaultComboBoxModel();
    private JComboBox daysbox = new JComboBox();


    public SettingP(){

        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        setLayout(new MigLayout("fillx"));
        setPreferredSize(new Dimension(500,500));
        openBtn.addActionListener((e) -> {
            int selcted = chooser.showOpenDialog(this);
            if (selcted == JFileChooser.APPROVE_OPTION){
                fileUrl.setText(chooser.getSelectedFile().getPath());
        }});




        for (String lang : langFiles){
            langBoxModel.addElement(lang.replace(".json",""));

        }
        langBox.setModel(langBoxModel);
        langBoxModel.setSelectedItem(langSetting);


        themeBox.setModel(themeModel);

        themeModel.addElement("Dark");
        themeModel.addElement("Light");

        themeBox.setSelectedItem(SettingManager.getInstance().getThemeUrl());

        fileUrl.setText(new File(SettingManager.getInstance().getSaveDataUrl()).getAbsolutePath());
        fileUrl.setEnabled(false);
        for (int i = 1; i <= 7; i++){
            daysModel.addElement(i);
        }
        daysbox.setModel(daysModel);
        daysbox.setSelectedItem(days);


        add(new JLabel(lang.get("Set_SaveData_Path").asString()),"grow,wrap");
        add(fileUrl, "grow,w 80%");
        add(openBtn, "wrap");
        add(new JLabel(lang.get("Set_Lang").asString()),"wrap");
        add(langBox, "span,growx,wrap");
        add(new JLabel(lang.get("Set_THEME").asString()),"wrap");
        add(themeBox,"span,growx,wrap");
        add(displayDays,"span,growx,wrap");
        add(daysbox,"span,growx");
        add(restart, "span,growx");

    }



    @Override
    public void action(ModalController controller, int action) {

        if (action == SimpleModalBorder.OK_OPTION){
            String lang = (String) langBoxModel.getSelectedItem();
            if (!fileUrl.getText().equals(new File(SettingManager.getInstance().getSaveDataUrl()).getAbsolutePath()) && DataFileManager.getInstance().isAllowMakingFile()){
                SettingManager.getInstance().setSaveDataUrl(fileUrl.getText());
                DataFileManager.getInstance().loadSaveData();
            }


            if (SettingManager.getInstance().getThemeUrl() != themeModel.getSelectedItem()) {
                SettingManager.getInstance().setThemeUrl((String) themeModel.getSelectedItem());
                FlatAnimatedLafChange.showSnapshot();
                SwingUtilities.invokeLater(() -> {
                    if (themeModel.getSelectedItem() == "Dark") {
                        FlatMacDarkLaf.setup();
                    } else {
                        FlatMacLightLaf.setup();
                    }
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();

                });


            }
            if ((int) daysbox.getSelectedItem() != SettingManager.getInstance().getBeforeDays()){
                SettingManager.getInstance().setBeforeDays((int) daysbox.getSelectedItem());
            }

            SettingManager.getInstance().setLang(lang);



            DataFileManager.getInstance().saveSetting();


        }





        }

}
