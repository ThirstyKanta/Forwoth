package com.kanta.components.timer.renderer;

import com.kanta.components.timer.MyTimer;
import com.kanta.rowdata.RowData;
import com.kanta.table.dialog.DialogBase;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TimePanel extends DialogBase implements ActionListener {
    private final JButton button;
    private final MyTimer timer = new MyTimer();
    public TimePanel(ArrayList<RowData> target){
        super(target);
        setLayout(new MigLayout("fillx"));
        TimeDisplayPane displayPane = new TimeDisplayPane();
        timer.setListener(displayPane);
        button = new JButton("Start");
        button.setActionCommand(button.getText().toUpperCase());
        button.addActionListener(this);
        setPreferredSize(new Dimension(300,300));
        add(displayPane, "wrap");
        add(button,"alignx center,aligny top");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd =e.getActionCommand();
        if (cmd.equals("START")) {
            timer.run();

        }else if(cmd.equals("STOP")){
            timer.stop();
        }else if(cmd.equals("Skip")){

        }

        if(timer.isRunning()){
            button.setText("Stop");
        }else if (timer.isInPomodoro()){

        }else{
            button.setText("Start");
        }

        button.setActionCommand(button.getText().toUpperCase());

    }

    public MyTimer getTimer() {
        return timer;
    }

    public boolean isTimerRunning(){
        return timer.isRunning();
    }
    public long getTotalTime(){
        return timer.getTotalTime();
    }


}
