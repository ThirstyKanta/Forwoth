/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.table.dialog;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.kanta.components.timer.utils.MyTimeUtils;
import com.kanta.rowdata.RowData;

import com.kanta.rowdata.RowDataManager;
import com.kanta.setting.LangManager;
import com.kanta.table.components.review.ForgettingCurveUtils;
import com.kanta.table.components.review.Review_panel;
import net.miginfocom.swing.MigLayout;
import raven.datetime.DatePicker;
import raven.datetime.TimePicker;
import raven.modal.component.SimpleModalBorder;
import raven.modal.listener.ModalCallback;
import raven.modal.listener.ModalController;
import tools.jackson.databind.JsonNode;

/**
 *
 * @author Kanta
 */
public class TodoContent extends DialogBase{
    private final JsonNode lang = LangManager.getInstance().getLang().get("detail_dialog");
    private JTextArea contentArea = null;
    private JScrollPane contentPane = null;
    private final Review_panel reviewPanel;
    private final DatePicker datePicker = new DatePicker();
    private final TimePicker timePicker = new TimePicker();
    private final JFormattedTextField textFieldDate = new JFormattedTextField();
    private final JFormattedTextField textFieldTime = new JFormattedTextField();


   public void init_variable(){

        contentArea = new JTextArea();
        contentPane = new JScrollPane(contentArea);
        contentArea.setEditable(false);
        contentArea.setText(targetRowData_arr.get(0).getContent());
        timePicker.setEditor(textFieldTime);
        datePicker.setEditor(textFieldDate);
        //contentArea.setPreferredSize(new Dimension(500,500));
        timePicker.setSelectedTime(targetRowData_arr.get(0).getLocalTime());
        datePicker.setSelectedDate(targetRowData_arr.get(0).getRecordedDate());
        textFieldDate.setEditable(false);
        textFieldTime.setEditable(false);
        timePicker.setEnabled(false);
        datePicker.setEnabled(false);

   }

    public Review_panel getReviewPanel() {
        return reviewPanel;
    }

    public TodoContent(ArrayList<RowData> targetRowData_arr) {
        super(targetRowData_arr);
        init_variable();
        setLayout(new MigLayout("fillx,insets 10","[]","[]"));
        setPreferredSize(new Dimension(500,500));
        reviewPanel = new Review_panel(targetRowData_arr);
        JLabel title = new JLabel(targetRowData_arr.get(0).getName());
        FlatSVGIcon icon = new FlatSVGIcon(targetRowData_arr.get(0).getSvgIcon(),20,20);
        int r = targetRowData_arr.get(0).getIconColor()[0];
        int g = targetRowData_arr.get(0).getIconColor()[1];
        int b = targetRowData_arr.get(0).getIconColor()[2];
        int a = targetRowData_arr.get(0).getIconColor()[3];
        icon.setColorFilter(new FlatSVGIcon.ColorFilter((color) -> new Color(r,g,b,a)));
        title.setIcon(icon);
        title.putClientProperty(FlatClientProperties.STYLE,"font: 18");
        JLabel detailLabel = new JLabel(lang.get("detail").asString());
        JLabel dataLabel = new JLabel(lang.get("date").asString());
        JLabel difficultyLabel = new JLabel(lang.get("difficulty").asString());
        JLabel totalTimeLabel = new JLabel(lang.get("TotalTime").asString());
        long elapsedTotal = targetRowData_arr.get(0).getTotalTime() / 1000;
        long hour = elapsedTotal / 3600;
        long minute = (elapsedTotal / 60) % 60;
        long second = (elapsedTotal % 60);
        JLabel totalTime = new JLabel(String.format(lang.get("time").asString(),hour,minute,second));
        detailLabel.putClientProperty(FlatClientProperties.STYLE,"font:15,bold");

        add(title,"growx,wrap,w ::50%, h 20::");

        add(detailLabel, "wrap,growx,h 20::");
        add(contentPane, "span,grow,align left,wrap, h 30%!");
        add(dataLabel,"wrap,grow,h 30::");
        add(textFieldDate,"growx,w 50%,h 30::");
        add(textFieldTime,"growx, w 50%,h 20::,wrap");
        add(totalTimeLabel,"wrap");
        add(totalTime, "wrap");
        add(difficultyLabel, "growx,wrap");
        add(reviewPanel,"span,grow,wrap,h 30::");




    }


    public void action() {

    }
}
