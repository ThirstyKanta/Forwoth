package com.kanta.components.timer.renderer;

import com.kanta.table.utils.ColorManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SimpleRenderer implements MyTimerRenderer{
    Color color = ColorManager.foreground;
    long second = 0;
    long minute =  0;
    long hour =  0;
    long current = 0;

    @Override
    public void renderTime(Graphics2D g2, TimeState state,int height,int width) {
        color = state != null && state.isInPomodoro() ? new Color(40, 188, 131,255): UIManager.getColor("Panel.foreground");

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if (state !=null) {
                //current = (state.getTime() - (state.getFocusMillTime() * state.getPomodoroCount()));
            current = state.getTime();
            long minus = state.getPomodoroCount() > 0 ? 1 :0 ;
               // current = Math.abs(current - state.getBreakTime() * minus);
               // System.out.println( state.getBreakTime() *minus + ":MAKE");

        }

        setTime(current);


        String time = String.format("%02d:%02d:%02d",hour,minute,second);


        Font font  =new Font("MS Pゴシック",Font.PLAIN,70);
        g2.setFont(font);
        FontMetrics fontMetrics = g2.getFontMetrics(font);

        int y = height / 2 - fontMetrics.getHeight() / 2 ;
        int x = width  / 2 - fontMetrics.stringWidth(time) / 2;

        g2.setColor(color);

        g2.drawString(time, x, y);
    }

    public void setTime(long current){
        this.second =   (current / 1000) % 60;
        this.minute = ((current / 1000) / 60) % 60;
        this.hour = current / (3600 * 1000) ;
    }

}
