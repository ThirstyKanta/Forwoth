/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kanta.rowdata;

import com.kanta.table.TodoTableModel;

import java.util.*;


/**
 *
 * @author Kanta
 */
public class RowDataManager {
    private static final RowDataManager instance = new RowDataManager();

    private static final TodoTableModel model = new TodoTableModel();
    private ArrayList<RowData> waitRowDataList = new ArrayList<>();



    public static TodoTableModel getModel() {
        return model;
    }

    public ArrayList<RowData> getWaitRowDataList() {
        return waitRowDataList;
    }


    public int setName(String name){
        ArrayList<String> names = new ArrayList<>();
        for (RowData rowData : model.getRowList()){
            names.add(rowData.getName());
        }
        for(RowData rowData : waitRowDataList){
            names.add(rowData.getName());
        }

        for (String value :names){
            if (name.equals(value)){
                System.out.println("Name Duplication");
                return 1;
            }
        }

        return 0;
    }


    //TODO:配列の名前の重複をチェックする関数だけど、かなり無理やりかも？ アルゴリズムとか知ってればかなり簡略化できそう。
    public int getDuplicateCheck(boolean fix){
        ArrayList<RowData> clone_rowList = model.getRowList();
        Map<RowData,ArrayList<RowData>> map = new HashMap<>();
        out:
        for (RowData temp : clone_rowList){
            ArrayList<RowData> rowData = new ArrayList<>();

            for (RowData key : map.keySet()) {
                for (int index = 0; index < map.get(key).size(); index++) {
                    if (map.get(key).get(index).equals(temp) || key.equals(temp)) {
                        continue out;
                    }
                }
            }

            for(RowData temp_2 : clone_rowList){
                String name = temp.getName().replaceAll("\\(\\d+\\)$","").trim();
                String tmp_name = temp_2.getName().replaceAll("\\(\\d+\\)$","").trim();

                System.out.println(name);
                if (Objects.equals(name, tmp_name) && !(temp.equals(temp_2))){
                    rowData.add(temp_2);
                    //System.out.println(String.format("%sと%sの重複",temp.getName(),temp_2.getName()));
                }
            }
            if (!rowData.isEmpty()) {
                map.put(temp, rowData);
            }
        }


        if (fix){
            if (!map.isEmpty()) {
                for (RowData key : map.keySet()) {
                    for (int i = 0; i < map.get(key).size(); i++) {
                        String name = map.get(key).get(i).getName().replaceAll("\\(\\d+\\)$", "").trim();
                        map.get(key).get(i).setName(String.format("%s (%d)", name, i + 1));
                        System.out.println(map.get(key).size());
                    }
                }
            }

        }else {
            System.out.println(map.size());
            return 1;
        }



        return 0;
    }


    public void moveToRowData(RowData row){
        model.addRowData(row);
        waitRowDataList.remove(row);
        model.fireTableRowsDeleted(0,model.getRowList().size());
    }
    public void moveToFinishData(RowData row){
        waitRowDataList.add(row);
        model.getRowList().remove(row);
        model.fireTableRowsDeleted(0,model.getRowList().size());
    }


    public static RowDataManager getInstance() {
        return instance;
    }



    public void setWaitRowDataList(ArrayList<RowData> waitRowDataList) {
        this.waitRowDataList = waitRowDataList;
    }

    public RowDataManager(){


    }

}
