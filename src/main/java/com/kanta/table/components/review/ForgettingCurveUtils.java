package com.kanta.table.components.review;

import com.kanta.rowdata.RowData;

public class ForgettingCurveUtils {



    public static float getCalReview(EnumReview review, RowData rowData) {
        for (EnumReview temp_review : EnumReview.values()) {
            if (review == temp_review) {

                float savingRate = rowData.getInitialTime() != 0 ? (float) rowData.getTodayTotal() / rowData.getInitialTime() : 0.3F ;
                System.out.println(String.format("%s / %s = %s", rowData.getTodayTotal(),rowData.getInitialTime(),savingRate ));
                if (rowData.getPrevious_day() <= 0 ) rowData.setPreviousDay(1);
                float days =  (rowData.getPrevious_day() * ((float) 1 / savingRate) * review.difficulty);
                System.out.println(String.format("%s * %s * %s", rowData.getPrevious_day(),((float) 1/ savingRate),review.difficulty));
                rowData.setPreviousDay(days);
                System.out.println(days + "日");
                return days;
            }
        }
        return 0;
    }

    public static void main(String[] args){
        RowData rowData = new RowData();
        rowData.setInitialTime(250 * 1000);
        rowData.setTotalTime(10 * 1000);
        rowData.setPreviousDay(1);
        getCalReview(EnumReview.HARD,rowData);
    }


}
