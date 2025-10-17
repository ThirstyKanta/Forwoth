package com.kanta.components.timer.utils;

public class MyTimeUtils {


    public static  String getTimeNanoToString(long timeNano){
        long second = (timeNano / 1000) % 60;
        long minute = ((timeNano / 1000) / 60) % 60;
        long hour =  (timeNano / 1000) / 3600;

        return String.format("%02d:%02d:%02d",hour,minute,second);
    }
    public static String getTimeAsString(long[] time){
        return String.format("%02d:%02d:%02d",time[0],time[1],time[2]);
    }

}
