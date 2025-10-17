/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.todoDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.kanta.components.iconjcombox.IconJCombox;
import com.kanta.components.iconjcombox.IconJComboxRenderer;
import com.kanta.rowdata.file.DataFileManager;
import com.kanta.rowdata.RowData;
import com.kanta.rowdata.RowDataManager;

import com.kanta.setting.LangManager;

import net.miginfocom.swing.MigLayout;
import raven.color.ColorPicker;
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
@SuppressWarnings("ALL")
public class AddToDo extends JPanel implements ModalCallback, ActionListener {
    private JsonNode lang = LangManager.getInstance().getLang().get("add_menu");
    private JTextField titleField = null;
    private JTextArea contentTextArea = null;
    private JScrollPane contentScrollPane = null;
    private DatePicker dataPicker = null;
    private TimePicker timePicker = null;

    private ColorPicker colorPicker = null;
    private JButton btnColorPicker = null;
    IconJComboxRenderer Myrenderer = new IconJComboxRenderer();

    private JFormattedTextField formattedTextFieldDate;
    private JFormattedTextField formattedTextFieldTime;
    private final int width = 500;
    private final int height = 500;

    private IconJCombox iconJCombox = null;



    public void init_variable(){

        this.titleField = new JTextField();
        this.contentTextArea = new JTextArea();
        this.contentScrollPane = new JScrollPane(contentTextArea);
        
        this.timePicker = new TimePicker();
        this.dataPicker = new DatePicker();

        this.colorPicker = new ColorPicker();
        colorPicker.setSelectedColor(UIManager.getColor("UI"));
        this.btnColorPicker = new JButton();


        Color themeColor = UIManager.getColor("Panel.background");
        int blue = 255 -themeColor.getBlue();
        int red = 255 - themeColor.getRed();
        int green = 255 -themeColor.getGreen();

        colorPicker.addColorChangedListener((color,event) -> Myrenderer.setCurrentColor(colorPicker.getSelectedColor()));


        this.formattedTextFieldDate = new JFormattedTextField();
        this.formattedTextFieldTime = new JFormattedTextField();

        this.iconJCombox = new IconJCombox();
        this.iconJCombox.setRenderer(Myrenderer);

        btnColorPicker.addActionListener(this);
        btnColorPicker.setActionCommand("colorpicker");
        btnColorPicker.setOpaque(true);
        btnColorPicker.setContentAreaFilled(false);




        btnColorPicker.setIcon(new FlatSVGIcon("image/icons/palette.svg",20,20).setColorFilter(new FlatSVGIcon.ColorFilter(
                (color) -> new Color(blue,red,green)
        )));

    }

    public void init_date_and_time(){
        timePicker.setEditor(formattedTextFieldTime);
        timePicker.setSelectedTime(LocalTime.now());

        dataPicker.setEditor(formattedTextFieldDate);
        dataPicker.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);
        dataPicker.setSelectedDate(LocalDate.now());
        dataPicker.setUsePanelOption(true);

    }

    public AddToDo() {
       
        setLayout(new MigLayout("fillx,wrap 2,insets 10","[fill,200]"));
        setPreferredSize(new Dimension(width,height));
        init_variable();
        init_date_and_time();
        

        this.titleField.putClientProperty(FlatClientProperties.STYLE, "arc:10");
        contentTextArea.setLineWrap(true);

        add(new JLabel(lang.get("Type_Title").asString()),"span,growx");
        add(titleField,"span,growx,wrap");
        add(new JLabel(lang.get("Select_Icon").asString()),"span,growx");
        add(iconJCombox,"growx,h 25!");
        add(btnColorPicker, "w 10%!,wrap");
        add(new JLabel(lang.get("Type_Data").asString()),"span,growx");
        add(formattedTextFieldDate,"growx");
        add(formattedTextFieldTime,"growx");
        add(new JLabel(lang.get("Type_Content").asString()),"wrap");
        add(contentScrollPane,"span,grow,h 100%");
    }


    @Override
    public void action(ModalController controller,int action) {
        if (action == SimpleModalBorder.OK_OPTION){
            String title = titleField.getText();
            String content = contentTextArea.getText();
            LocalDate localdate = dataPicker.getSelectedDate();
            LocalTime localTime = timePicker.getSelectedTime();
            Color selected_color = colorPicker.getSelectedColor();
            int[] color = {selected_color.getRed(),selected_color.getGreen(),selected_color.getBlue(),selected_color.getAlpha()};
            FlatSVGIcon svgIcon = (FlatSVGIcon) iconJCombox.getSelectedItem();
            RowData r = new RowData(title,content,localdate,localTime);
            r.setIconColor(color);
            r.setSvgIcon(svgIcon.getName());
            javax.swing.SwingUtilities.invokeLater(() -> {
                RowDataManager.getModel().addRowData(r);
                RowDataManager.getInstance().getDuplicateCheck(true);
                DataFileManager.getInstance().createSaveData();
            });
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if(cmd.equals("colorpicker")){
         ColorPicker.showDialog(this,"Color Picker", colorPicker);
        }

    }
}
