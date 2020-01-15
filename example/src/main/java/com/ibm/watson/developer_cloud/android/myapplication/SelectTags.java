package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectTags extends AppCompatActivity {
    private int selectedIndex1, selectedIndex2, selectedIndex3, nextSelect = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_tags);
        ArrayList<String> tags = Data.getUniqueTags();
        for (String s : tags){

        }
    }
}
