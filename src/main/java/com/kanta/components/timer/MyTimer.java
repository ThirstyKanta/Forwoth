package com.kanta.components.timer;

import com.kanta.components.timer.listener.MyTimerListener;
import com.kanta.components.timer.renderer.TimeState;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyTimer {


    private ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
    private long startTime;
    private MyTimerListener listener;
    private  boolean running = false;

    //Pomodoro
    private boolean inPomodoro = false;
    private final boolean modePomodoro = false;
    private long focusMillTime = 15 * 1000;
    private long breakTime = 5 * 1000;
    private long longBreakTime = 10 * 1000;
    private AtomicInteger pomodoroCount = new AtomicInteger();
    private final int maxPomodoroCount = 4;
    private long totalTime = 0;
    //
    public MyTimer(){
        scheduled.scheduleAtFixedRate(this::timeSystem, 1, 1, TimeUnit.SECONDS);
        SchuduleTimerManager.register(scheduled);
    }
    public void timeSystem(){
        if (running){
            long current = System.currentTimeMillis() - startTime;
            long elapsedSecond = current / 1000 % 60;
            long currentBreakTime = (long) (current - focusMillTime * Math.floor((double) current / focusMillTime));


            //
            //System.out.println(  ((focusMillTime / 1000) % 60) + ((breakTime/1000)  * pomodoroCount.get()));
            //System.out.println(elapsedSecond +":" + ((focusMillTime / 1000) % 60) + ":" + inPomodoro);
            if (!inPomodoro  && modePomodoro && (elapsedSecond ) % ((focusMillTime / 1000) % 60)  == 0 && elapsedSecond != 0){
                pomodoroCount.incrementAndGet();
                inPomodoro = true;
            }else if (inPomodoro){
                if ((breakTime - currentBreakTime)  <= 0   && pomodoroCount.get() < maxPomodoroCount){
                        inPomodoro = false;
                }else if((longBreakTime - currentBreakTime) <= 0 && pomodoroCount.get() >= maxPomodoroCount){
                        inPomodoro = false;
                }
               // System.out.println(current / 1000 + ":::::");
            }else {
              //  System.out.println(current / 1000);
            }

            listener.update(new TimeState(current,focusMillTime,breakTime,longBreakTime,pomodoroCount.get(), inPomodoro,running));



        }
    }

    public static  void main(String[] args){


    }

    public void run(){
        if (!running ) {
            startTime = System.currentTimeMillis();
            setRunning(true);

        }
    }

    public void stop(){
        setRunning(running = false);
        inPomodoro = false;
        totalTime += System.currentTimeMillis() - startTime;
        System.out.println(totalTime/1000);
    }


    public void setListener(MyTimerListener listener) {
        if (listener != null) this.listener = listener;
    }

    private void setRunning(boolean running){
        this.running = running;
        if (listener != null)listener.isRunningListener();
    }

    public MyTimerListener getListener() {
        return listener;
    }


    public void setBreakTime(long breakTime) {
        this.breakTime = breakTime;
    }

    public void setLongBreakTime(long longBreakTime) {
        this.longBreakTime = longBreakTime;
    }

    public void setFocusMillTime(long focusMillTime) {
        if (breakTime < focusMillTime && longBreakTime < focusMillTime) {
            this.focusMillTime = focusMillTime;
        }
    }


    public boolean isRunning() {
        return running;
    }

    public boolean isInPomodoro() {
        return inPomodoro;
    }

    public long getTotalTime() {
        return totalTime;
    }


}
