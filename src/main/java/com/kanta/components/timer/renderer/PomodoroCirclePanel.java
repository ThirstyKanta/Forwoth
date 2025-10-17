package com.kanta.components.timer.renderer;

import com.kanta.table.utils.ColorManager;

import javax.swing.*;
import java.awt.*;

public class PomodoroCirclePanel extends JPanel {

    public PomodoroCirclePanel(){
        setPreferredSize(new Dimension(500,300));
    }

    private int satisfyingNumbers = 0;
    private final int maxSatisfyingNumbers = 4;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        drawCircle(g2,20,4, satisfyingNumbers);
    }

    public void setSatisfyingNumbers(int satisfyingNumbers) {
        if (satisfyingNumbers < maxSatisfyingNumbers) {
            this.satisfyingNumbers = satisfyingNumbers;
        }
    }

    public void drawCircle(Graphics2D g2, int r, int size, int satisfying_numbers){
        // S = L-nW/n+1
        // L = getWidth()
        // W = 2*r
        // n = size
        Color fillColor = new Color(40, 188, 131, 255);
        int space = (getWidth() - size*2*r) / size - 1;
        int y = getHeight() / 2  - r;
        for (int i = 0; i < size; i++) {

            int x = (2 * r + space) * i + space;

            if (i <= satisfying_numbers - 1){

                g2.setColor(fillColor);
                System.out.println("fill:" + i);
                g2.fillOval(x,y,r,r);

            }else {
                g2.setColor(ColorManager.foreground);
                System.out.println("draw:" + i);
                g2.drawOval(x, y, r, r);
            }

        }
    }


}
