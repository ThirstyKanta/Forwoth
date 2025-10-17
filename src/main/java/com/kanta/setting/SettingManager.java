package com.kanta.setting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use =JsonTypeInfo.Id.CLASS)
@JsonSubTypes({@JsonSubTypes.Type(name = "S",value = SettingManager.class)})
public class SettingManager {


    @JsonProperty("savedata_url")
    private  String saveDataUrl = null;

    @JsonProperty("before-days")
    private int beforeDays = 2;

    @JsonProperty("theme")
    private  String themeUrl = null;

    @JsonProperty("lang")
    private  String lang = null;
    @JsonProperty("")
    private boolean displayFlags = false;
    @JsonIgnore
    private static final SettingManager instance = new SettingManager();



    /**
     *
     * @param lang 言語設定
     * @param themeUrl Themeの選択*
     * @param beforeDays 何日前に表示するか
     * @param saveDataUrl セーブデータ保存先
     */
    public void load(String lang,String themeUrl,int beforeDays,String saveDataUrl,boolean displayFlags) {
        this.lang = lang;
        this.themeUrl = themeUrl;
        this.beforeDays = beforeDays;
        this.saveDataUrl = saveDataUrl;
        this.displayFlags = displayFlags;
        //System.out.println(saveDataUrl);
    }

    public static SettingManager getInstance() {
        return instance;
    }

    public synchronized String getThemeUrl() {
        if (themeUrl == null) return "Dark";
        else return themeUrl;
    }

    public synchronized boolean getDisplayFlags() {
        return displayFlags;
    }

    public void setDisplayFlags(boolean displayFlags) {
        this.displayFlags = displayFlags;
    }

    public synchronized int getBeforeDays() {
        return beforeDays;
    }

    public synchronized void setBeforeDays(int beforeDays) {
        this.beforeDays = beforeDays;
    }

    public synchronized String getLang() {
        if (lang == null)  return "ja_jp";
        else return  lang;
    }

    public synchronized void setLang(String lang) {
        this.lang = lang;
    }

    public synchronized String getSaveDataUrl() {
        if (saveDataUrl == null) return "Forwoth\\data\\SaveData.json";
        else return saveDataUrl;
    }

    public synchronized void setSaveDataUrl(String saveDataUrl) {
        System.out.println(saveDataUrl + "DDD") ;
        this.saveDataUrl = saveDataUrl;
    }

    public synchronized  void setThemeUrl(String themeUrl) {
        this.themeUrl = themeUrl;
    }
}
