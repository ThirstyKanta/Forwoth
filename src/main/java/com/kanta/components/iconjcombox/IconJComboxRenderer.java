package com.kanta.components.iconjcombox;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class IconJComboxRenderer extends DefaultListCellRenderer {

    FlatSVGIcon flatSVGIcon;
    JLabel iconLabel;
    Color currentColor;



    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        iconLabel = new JLabel();

        flatSVGIcon = (((FlatSVGIcon) value).setColorFilter(new FlatSVGIcon.ColorFilter(color -> {

            if (currentColor != null) {
                return currentColor;
            } else {
                return null;
            }
        })));
        iconLabel.setIcon(flatSVGIcon);

        return iconLabel;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
}
