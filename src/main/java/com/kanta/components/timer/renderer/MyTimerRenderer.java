package com.kanta.components.timer.renderer;

import javax.swing.*;
import java.awt.*;

public interface MyTimerRenderer {

    public void renderTime(Graphics2D g2,TimeState state,int height,int width);

}
