package com.kanta.rowdata.file;


import com.kanta.Main;
import com.kanta.rowdata.RowDataManager;
import com.kanta.setting.LangManager;
import com.kanta.setting.SettingManager;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataFileManager {


    public static final DataFileManager instance = new DataFileManager();
    private final ObjectMapper mapper = new ObjectMapper();

    private final JsonNode lang = LangManager.getInstance().getLang().get("loading");

    private final File settingJson =  new File("Forwoth\\Setting\\setting.json") ;
    private File savedataJson;



    public void loadSaveData(){
        savedataJson = getSaveFile();
        loadSaveData(savedataJson);
    }

    public void loadSaveData(File file){


            try {
                    System.out.println( "DD:"+ file.getPath());
                if (file.exists()) {
                    DataWrapper dataWrapper = mapper.readValue(file, new TypeReference<>() {
                    });


                    RowDataManager.getModel().setRowList(dataWrapper.rowList);
                    RowDataManager.getInstance().getDuplicateCheck(true);

                    RowDataManager.getInstance().setWaitRowDataList(dataWrapper.waitRowList);
                } else {
                    if (isAllowMakingFile()){
                        boolean mkdirs = file.getParentFile().mkdirs();
                        try {
                            boolean isSuccess = file.createNewFile();
                            createSaveData(file,false);
                            Toast.show(Main.getInstance(), Toast.Type.SUCCESS,"SUCCESS");
                        } catch (IOException e) {
                            Toast.show(Main.getInstance(), Toast.Type.WARNING, lang.get("unexpected_error").asString());
                            e.printStackTrace();
                        }
                    }

                }
            } catch (JacksonException e) {
                ModalDialog.showModal(Main.getInstance(),new SimpleModalBorder(new JLabel(e.getMessage()),"",SimpleModalBorder.OK_OPTION,null));
            }

    }

    public void export(){
        ExportFileChooser chooser = new ExportFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new JsonFileFilter());
        chooser.showSaveDialog(Main.getInstance());
    }

    public boolean isAllowMakingFile() {
        System.out.println(settingJson.exists());
        return settingJson.exists();
    }

    public boolean createSaveData(File file, boolean isShowToast) {
            if (file != null) {
                try {
                    DataWrapper wrapper = new DataWrapper();
                    wrapper.setRowList(RowDataManager.getModel().getRowList());
                    wrapper.setWaitrowList(RowDataManager.getInstance().getWaitRowDataList());
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
                    if (isShowToast) Toast.show(Main.getInstance(), Toast.Type.SUCCESS, "Saved");
                    return true;
                } catch (JacksonException e) {
                    if (isShowToast) {
                        Toast.show(Main.getInstance(), Toast.Type.WARNING, e.getMessage());
                        e.printStackTrace();
                    } else {
                        e.printStackTrace();
                    }
                    return false;
                }
            }
            return false;
    }

    public boolean createSaveData() {
        return createSaveData(savedataJson,true);
    }

    public void setSaveDataJson(File savedataJson) {
        this.savedataJson = savedataJson;
    }

    public boolean saveSetting(File file){
        if (file.exists()){
            mapper.writerWithDefaultPrettyPrinter().writeValue(file,SettingManager.getInstance());
            return  true;

        }else {
            if (isAllowMakingFile()){
                boolean isSuccess = file.getParentFile().mkdirs();

                try {
                    boolean isExistFile = file.createNewFile();
                    return isExistFile && isSuccess;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }


    public boolean saveAll(){
        return saveSetting() && createSaveData();

    }
    public boolean saveSetting(){
        return saveSetting(settingJson);
    }


    public File getSaveFile(){

        if (! (new File(SettingManager.getInstance().getSaveDataUrl()).getAbsoluteFile().equals(new File("Forwoth\\data\\SaveData.json").getAbsoluteFile())) ){

            return new File(SettingManager.getInstance().getSaveDataUrl() + "\\Forwoth\\data\\SaveData.json");
        }else {
            return new File(SettingManager.getInstance().getSaveDataUrl());
        }

    }


    public boolean loadSetting(){
        return loadSetting(settingJson,false);
    }

    public boolean loadAll(){
        return loadAll(settingJson);
    }
    public boolean loadAll(File file){
        if(!loadSetting(file,false)){
            return true;
        }else {
            loadSetting();
            loadSaveData();
            return false;
        }
    }

    public void warningSettingFile(boolean isWarning){
        if (isWarning) {
            File file = settingJson;
            JPanel tmp_warning_p = new JPanel();
            tmp_warning_p.setLayout(new MigLayout("fillx,wrap,insets 15"));
            tmp_warning_p.add(new JLabel(lang.get("file_warning").asString() + "\n"));
            tmp_warning_p.add(new JLabel(file.getAbsolutePath()));
            ModalDialog.showModal(Main.getInstance(), new SimpleModalBorder(tmp_warning_p, "", SimpleModalBorder.OK_CANCEL_OPTION, (controller, action) -> {
                if (action == SimpleModalBorder.OK_OPTION) {
                    boolean mkdirs = file.getParentFile().mkdirs();
                    try {
                        boolean isSuccess = file.createNewFile();
                        saveSetting(file);
                        Toast.show(Main.getInstance(), Toast.Type.SUCCESS, "SUCCESS");

                    } catch (IOException e) {
                        Toast.show(Main.getInstance(), Toast.Type.WARNING, lang.get("unexpected_error").asString());
                        System.out.println("これよな？");
                        e.printStackTrace();
                    }

                    loadSaveData();
                }
            }));
        }
    }


    public boolean loadSetting(File file, boolean isShowToast) {
        if (file.exists()) {
            SettingManager manager = mapper.readValue(file, new TypeReference<>() {
            });
            System.out.println("HELLO?:"+manager.getSaveDataUrl());
            SettingManager.getInstance().load(manager.getLang(),manager.getThemeUrl(),manager.getBeforeDays(),manager.getSaveDataUrl(),manager.getDisplayFlags());
        }
        return file.exists();
    }

    public static DataFileManager getInstance() {
        return instance;
    }

}
