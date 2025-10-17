/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.setting;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


/**
 *
 * @author Kanta
 */
public class LangManager {
    private static LangManager instance = new LangManager();
    private final String mainLangId = "/lang/"+ SettingManager.getInstance().getLang() + ".json";


    private final ObjectMapper mapper = new ObjectMapper();
    private final JsonNode langs ;
    
    public LangManager(){
        langs =  mapper.readValue(getClass().getResourceAsStream(mainLangId), new TypeReference<>(){});
    }

    public JsonNode getLang(){
        return langs;
    }


    public static LangManager getInstance(){
        if (instance != null){
            instance = new LangManager();
        }
        return instance; 
    }

}
