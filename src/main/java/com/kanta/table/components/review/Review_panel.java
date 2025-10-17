package com.kanta.table.components.review;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.kanta.rowdata.RowData;
import com.kanta.setting.LangManager;
import net.miginfocom.swing.MigLayout;
import tools.jackson.databind.JsonNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("ALL")
public class Review_panel extends JPanel implements ItemListener {

    private JsonNode lang = LangManager.getInstance().getLang().get("review_kinds");
    EnumReview[] reviews = EnumReview.values();
    private JComboBox<EnumReview> comboBox = new JComboBox<>(reviews);

    public JComboBox<EnumReview> getComboBox() {
        return comboBox;
    }

    public Review_panel(ArrayList<RowData> targetRowData_arr){
        setLayout(new MigLayout("fill","[]push[]push[]"));
        comboBox.addItemListener(this);
        comboBox.setOpaque(true);

        add(comboBox,"span,grow");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){



        }
    }


}
