package com.kanta.table.dialog;

import com.kanta.Main;
import com.kanta.components.timer.renderer.TimePanel;
import com.kanta.rowdata.RowData;
import com.kanta.rowdata.RowDataManager;
import com.kanta.rowdata.file.DataFileManager;
import com.kanta.setting.LangManager;
import com.kanta.setting.SettingManager;
import com.kanta.table.components.review.EnumReview;
import com.kanta.table.components.review.ForgettingCurveUtils;
import com.kanta.table.utils.ColorManager;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.listener.ModalCallback;
import raven.modal.listener.ModalController;
import raven.modal.option.Option;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContentPanel extends DialogBase implements ModalCallback {
        private final JsonNode lang = LangManager.getInstance().getLang().get("CONTENT");

        TodoContent todoContent = null;
        TimePanel timePanel;
        public ContentPanel(ArrayList<RowData> target){
        super(target);
        setLayout(new MigLayout("fillx","[][]","[]10[]"));
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);

        Border border = new BevelBorder(BevelBorder.RAISED);
        separator.setBorder(border);
        separator.setBackground(ColorManager.foreground);
        separator.setPreferredSize(new Dimension(1,500));

        timePanel = new TimePanel(target);
        todoContent =new TodoContent(target);
        add(todoContent, "");
        add(separator,"");
        add(timePanel,"");

    }




    @Override
    public void action(ModalController controller, int action) {
    if (action != SimpleModalBorder.OPENED) {
        if (timePanel.isTimerRunning()) {
            controller.consume();
            Toast.show(Main.getInstance(), Toast.Type.DEFAULT,lang.get("running_delete").asString());
        } else if (action == SimpleModalBorder.CANCEL_OPTION || action == SimpleModalBorder.NO_OPTION){
            if (timePanel.getTotalTime() > 0){
                ModalDialog.showModal(this, new SimpleModalBorder(new JLabel(lang.get("CONTENT_CLOSE_WARNING").asString()), "", new SimpleModalBorder.Option[]{
                        new SimpleModalBorder.Option("保存する",SimpleModalBorder.YES_OPTION),new SimpleModalBorder.Option("キャンセル",SimpleModalBorder.NO_OPTION)
                }, new ModalCallback() {
                    @Override
                    public void action(ModalController controller, int action) {
                        if (action == SimpleModalBorder.NO_OPTION){
                            action_ok();
                        }
                    }
                }));
            }
        }else if (action == SimpleModalBorder.OK_OPTION) {
            if (timePanel.getTotalTime() > 0){
                action_ok();
            }

        }

    }
    else{
        RowData rowData = targetRowData_arr.get(0);
        if(!(rowData.getInitialTime() < 0)){
            if (!SettingManager.getInstance().getDisplayFlags()){

                JOptionPane.showConfirmDialog(Main.getInstance(),lang.get("CONTENT_FIRST").asString(),"INFO",JOptionPane.OK_CANCEL_OPTION);
                SettingManager.getInstance().setDisplayFlags(true);
            }
        }
    }
    }



    public Option getOptions(){
        Option option = ModalDialog.createOption();
        option.setBackgroundClickType(Option.BackgroundClickType.BLOCK);

        return option;
    }

    public void action_ok(){
        RowData rowData = targetRowData_arr.get(0);
        if (rowData != null) {
            EnumReview selected = (EnumReview) todoContent.getReviewPanel().getComboBox().getSelectedItem();
            rowData.setTotalTime(timePanel.getTotalTime());
            int days = (int) Math.floor(ForgettingCurveUtils.getCalReview(selected, rowData));
            LocalDate localDate = LocalDate.now().plusDays(days + targetRowData_arr.get(0).getInterval());
            System.out.println(localDate);
            rowData.setNextSchedule(localDate);
            if (rowData.getInterval() > 2) {
                RowDataManager.getInstance().moveToFinishData(rowData);
            }
            DataFileManager.getInstance().createSaveData();
        }
    }

}
