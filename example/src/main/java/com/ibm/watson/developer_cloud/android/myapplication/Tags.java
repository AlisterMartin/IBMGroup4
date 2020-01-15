package com.ibm.watson.developer_cloud.android.myapplication;

import java.util.ArrayList;

public class Tags {
    public ArrayList<String> tags;
    public ArrayList<Double> score;
    public ArrayList<String> url;
    public ArrayList<String> relavantTitle;


    public Tags(ArrayList<String> tags, ArrayList<Double> score, ArrayList<String> url, ArrayList<String> relavantTitle) {
        this.tags = tags;
        this.score = score;
        this.url = url;
        this.relavantTitle = relavantTitle;
    }
}
