package com.kanta.components.timer.listener;

import com.kanta.components.timer.renderer.TimeState;

public interface MyTimerListener {

    public void update(TimeState state);
    public void isRunningListener();
    public void isStoppedListener();


}
