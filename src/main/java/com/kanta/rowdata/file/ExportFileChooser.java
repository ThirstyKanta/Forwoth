package com.kanta.rowdata.file;

import com.kanta.Main;
import com.kanta.setting.LangManager;
import org.apache.commons.io.FilenameUtils;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import tools.jackson.core.exc.JacksonIOException;
import tools.jackson.databind.JsonNode;

import javax.swing.*;
import java.io.File;

public class ExportFileChooser extends JFileChooser {
    private final JsonNode lang = LangManager.getInstance().getLang().get("export");


    @Override
    public void setSelectedFile(File file) {
        if (file != null) {
                if (!(file.isFile() && FilenameUtils.getExtension(file.getName()).equals("json")) && file.isDirectory()) {
                    String fileName = "\\SaveData.json";
                    file = new File(file.getPath() + fileName);
                }
        }
        super.setSelectedFile(file);
    }

    @Override
    public void approveSelection() {

        if (getSelectedFile().exists()) {
            ModalDialog.showModal(this, new SimpleModalBorder(new JLabel(lang.get("exist_file").asString()), "", SimpleModalBorder.OK_CANCEL_OPTION,
                    ((controller, action) -> {

                    if (action == SimpleModalBorder.OK_OPTION) {
                        if (FilenameUtils.getExtension(getSelectedFile().getName()).equals("json"))
                            DataFileManager.getInstance().createSaveData(getSelectedFile(), true);
                           super.approveSelection();
                       }
                    })));
            return;
        }else {
            try {
                if (!FilenameUtils.getExtension(getSelectedFile().getName()).equals("json"))
                {
                    DataFileManager.getInstance().createSaveData(new File(getSelectedFile().getPath() +".json"),true);
                }else {
                    DataFileManager.getInstance().createSaveData(getSelectedFile(),true);
                }

                Toast.show(Main.getInstance(), Toast.Type.DEFAULT, "Exported");

            } catch (JacksonIOException e) {
                Toast.show(Main.getInstance(), Toast.Type.WARNING, e.getMessage());

            }
            super.approveSelection();
        }

    }



}
