package com.ui.backend.entity;

import com.ui.backend.DataManager;

import java.io.IOException;
import java.util.ArrayList;

import static com.ui.backend.DataManager.lists;

public class datapoint {
    private String at;
    private String value;
    private String Id;



    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public static void main(String[] args) throws IOException {

        ArrayList<ArrayList<datapoint>> tmp = DataManager.getInstance().lists;

        for(ArrayList<datapoint> xx:tmp){
            for(datapoint xxx: xx){
                System.out.println(xxx.getAt());
                System.out.println(xxx.getId());
                System.out.println(xxx.getValue());

            }
        }
    }
}
