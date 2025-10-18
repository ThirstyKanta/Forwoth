/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.rowdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Kanta
 */
@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS)
public class RowData {
    @JsonProperty("interval")
    private int interval = 0;
    @JsonProperty("name")
    private String name = null;
    @JsonProperty("content")
    private String content = null;
    @JsonProperty("Recorded-Date")
    private LocalDate recordedDate = null;
    @JsonProperty("Next-Schedule")
    private LocalDate nextSchedule = null;
    @JsonProperty("LocalTime")
    private LocalTime localTime = null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("svgIcon")
    private String svgIconFileName = null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int[] iconColor = null;

    @JsonIgnore
    private long todayTotal;

    @JsonProperty("Total_Time")
    private long totalTime;
    @JsonProperty("Initial_Time")
    private long initialTime;
    @JsonProperty("previous_day")
    private float previous_day;


    @Deprecated
    public RowData(){}

    /**
     *
     * @param name Todoの名前に当たります。インターバルを設定します。正直
     * @param content Todoの内容を記載します。
     * @param recordedDate 記録日を記録します。
     * @param time 記録時間を記録します。
     */
    public RowData(String name, String content, LocalDate recordedDate, LocalTime time){
        this.name = name;
        this.content = content;
        this.recordedDate = recordedDate;
        this.localTime = time;

    }


      @Deprecated
      public RowData(String name, int interval){
        this.name = name;
        this.interval = interval;
        this.content = null;
    }


    public void setPreviousDay(float previous_day) {
        this.previous_day = previous_day;
    }

    public float getPrevious_day() {
        return previous_day;
    }

    public void setTotalTime(long totalTime) {
        if (initialTime <= 0) initialTime = totalTime;
        this.totalTime += totalTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }

    public void setTodayTotal(long todayTotal) {
        this.todayTotal = todayTotal;
    }

    public long getTodayTotal() {
        return todayTotal;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public int[] getIconColor() {
        return iconColor;
    }

    public void setIconColor(int[] iconColor) {
        this.iconColor = iconColor;
    }

    public String getSvgIcon() {
        return svgIconFileName;
    }

    public LocalDate getNextSchedule() {
        return nextSchedule;
    }

    public void setSvgIcon(String path) {
        this.svgIconFileName = path;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }


    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setNextSchedule(LocalDate nextSchedule) {
        this.nextSchedule = nextSchedule;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getInterval(){
        if (nextSchedule == null){
            return 0;
        }else {
            return nextSchedule.compareTo(LocalDate.now());
        }
    }


    @Override
    public String toString() {
        return String.format("name = %s interval = %d", name,interval);
    }


    

}
