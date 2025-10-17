package com.kanta.components.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class SchuduleTimerManager {
    private static final List<ScheduledExecutorService> scheduledExecutorServiceList = new ArrayList<>();

    public static void register(ScheduledExecutorService service){
        scheduledExecutorServiceList.add(service);
    }
    public static void shutdownAll(){
        for(ScheduledExecutorService service: scheduledExecutorServiceList){
            if(!service.isShutdown()){
                service.shutdown();
            }
        }
        scheduledExecutorServiceList.clear();
    }

}
