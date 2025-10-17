package com.kanta.table.components.review;

import com.kanta.setting.LangManager;

import java.util.Map;

public enum EnumReview {

    EASY(3F),NORMAL(2.5F),HARD(1F);

    public float getDifficulty() {
        return difficulty;
    }

    final float difficulty;

    EnumReview(float difficulty){
        this.difficulty = difficulty;
    }



}
