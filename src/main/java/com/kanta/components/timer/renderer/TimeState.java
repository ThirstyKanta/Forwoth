package com.kanta.components.timer.renderer;

public class TimeState {
    private final long time;
    private final long breakTime;
    private final long longBreakTime;
    private final long focusMillTime;
    private final int pomodoroCount;
    private final boolean inPomodoro;
    private final boolean isRunning;


    public TimeState(long time,long focusMillTime,long breakTime,long longBreakTime,int pomodoroCount,boolean inPomodoro,boolean isRunning){
        this.time = time;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
        this.pomodoroCount = pomodoroCount;
        this.inPomodoro = inPomodoro;
        this.isRunning = isRunning;
        this.focusMillTime = focusMillTime;
    }

    public long getFocusMillTime() {
        return focusMillTime;
    }

    public int getPomodoroCount() {
        return pomodoroCount;
    }

    public long getBreakTime() {
        return breakTime;
    }

    public long getLongBreakTime() {
        return longBreakTime;
    }

    public long getTime() {
        return time;
    }

    public boolean isInPomodoro() {
        return inPomodoro;
    }



    public boolean isRunning() {
        return isRunning;
    }


}
