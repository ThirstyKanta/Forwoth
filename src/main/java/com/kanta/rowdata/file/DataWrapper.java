package com.kanta.rowdata.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kanta.rowdata.RowData;

import java.util.ArrayList;

@JsonTypeInfo(use =JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "D",value = DataWrapper.class)
})
class DataWrapper{

    @JsonProperty("RowList")
    ArrayList<RowData> rowList;
    @JsonProperty("Wait-RowList")
    ArrayList<RowData> waitRowList;

    public DataWrapper(){

    }


    public void setRowList(ArrayList<RowData> rowList) {
        this.rowList = rowList;
    }

    public void setWaitrowList(ArrayList<RowData> wait_rowList) {
        this.waitRowList = wait_rowList;
    }


}