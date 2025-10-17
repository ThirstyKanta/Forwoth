package com.kanta.components.timer.renderer;

import com.kanta.components.timer.listener.MyTimerListener;

import javax.swing.*;
import java.awt.*;

public class TimeDisplayPane extends JPanel implements MyTimerListener {

    private final MyTimerRenderer renderer = new SimpleRenderer();
    private TimeState state;

    public TimeDisplayPane(){
        setPreferredSize(new Dimension(300,300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (renderer != null ){
          renderer.renderTime((Graphics2D) g,state,getHeight(),getWidth());
        }

    }


    @Override
    public void update(TimeState state) {
        if (state != null) this.state = state;
        SwingUtilities.invokeLater(this::repaint);

    }

    @Override
    public void isRunningListener() {

    }

    @Override
    public void isStoppedListener() {

    }


}
