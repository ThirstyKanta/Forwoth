package com.kanta.table.components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class DaysLabel extends JPanel {
    private final JLabel days;
    private final JLabel text;
    public DaysLabel(){
        setLayout(new MigLayout("alignx center, aligny center","[][]"));
        days = new JLabel();
        text = new JLabel("days");
        add(days);
        add(text);
    }
    public void setDays(int day){
        days.setText(String.valueOf(day));
    }

    public JLabel getDays() {
        return days;
    }

    public JLabel getText() {
        return text;
    }
}
