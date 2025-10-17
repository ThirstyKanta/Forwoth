package com.kanta.table.utils;

import javax.swing.*;
import java.awt.*;

public class ColorManager {
    public static Color background = UIManager.getColor("Panel.background");
    public static Color foreground = UIManager.getColor("Panel.foreground");
    public static Color oppositeColorsForeground = new Color(255-foreground.getRed(),255-foreground.getGreen(),255-foreground.getBlue());
    public static Color oppositeColorsBackground = new Color(255-background.getRed(),255-background.getGreen(),255-background.getBlue());







}
