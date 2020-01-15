package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SelectTags extends AppCompatActivity {
    private ArrayList<Integer> tagIndexes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int tempViewID;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_tags);
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        final int width = (dm.widthPixels - 120)/3;
        ArrayList<String> tags = Data.getUniqueTags();
        final ConstraintLayout layout = findViewById(R.id.innerLayout);
        TextView text1 = new TextView(getApplicationContext());
        text1.setText(tags.get(0));
        text1.setId(View.generateViewId());
        tempViewID = text1.getId();
        text1.setBackgroundResource(R.color.lightRed);
        text1.setTextSize(20);
        text1.setPadding(20, 20, 20, 20);
        text1.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        final int tmpid1= text1.getId();
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                for (int i = 0; i < tagIndexes.size(); i++){
                    if (!flag && tagIndexes.get(i) == 0){
                        System.out.println(0 + " deactivate");
                        tagIndexes.remove(i);
                        layout.getViewById(tmpid1).setBackgroundResource(R.color.lightRed);
                        flag = true;
                    }
                }
                if (!flag){
                    System.out.println(0 + " activate");
                    tagIndexes.add(0);
                    layout.getViewById(tmpid1).setBackgroundResource(R.color.lightGreen);
                }
            }
        });
        layout.addView(text1);
        ConstraintSet set1 = new ConstraintSet();
        set1.clone(layout);
        set1.connect(text1.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 400);
        set1.connect(text1.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
        set1.applyTo(layout);
        for (int i = 1; i < tags.size(); i++){
            TextView text = new TextView(getApplicationContext());
            text.setText(tags.get(i));
            text.setId(View.generateViewId());
            text.setBackgroundResource(R.color.lightRed);
            text.setTextSize(20);
            text.setPadding(20, 20, 20, 20);
            text.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            final int temp = i;
            final int tmpid= text.getId();
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean flag = false;
                    for (int i = 0; i < tagIndexes.size(); i++){
                        if (!flag && tagIndexes.get(i) == temp){
                            System.out.println(temp + " deactivate");
                            tagIndexes.remove(i);
                            layout.getViewById(tmpid).setBackgroundResource(R.color.lightRed);
                            flag = true;
                        }
                    }
                    if (!flag){
                        System.out.println(temp + " activate");
                        tagIndexes.add(temp);
                        layout.getViewById(tmpid).setBackgroundResource(R.color.lightGreen);
                    }
                }
            });
            layout.addView(text);
            ConstraintSet set = new ConstraintSet();
            set.clone(layout);
            set.connect(text.getId(), ConstraintSet.TOP, tempViewID, ConstraintSet.BOTTOM, 30);
            set.connect(text.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
            tempViewID = text.getId();
            set.applyTo(layout);
        }
    }

}
