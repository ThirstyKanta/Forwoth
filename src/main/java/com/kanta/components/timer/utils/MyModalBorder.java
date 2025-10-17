package com.kanta.components.timer.utils;

import raven.modal.component.SimpleModalBorder;
import raven.modal.listener.ModalCallback;

import java.awt.*;

public class MyModalBorder extends SimpleModalBorder {



    public MyModalBorder(Component component, String title, int optionType, ModalCallback callback) {
        super(component, title, optionType, callback);
    }

}
