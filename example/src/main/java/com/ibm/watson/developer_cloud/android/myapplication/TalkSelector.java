package com.ibm.watson.developer_cloud.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TalkSelector extends AppCompatActivity {
    private ArrayList<Integer> talkIndexes = new ArrayList<>();
    private ArrayList<Integer> possibleTalks = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.talk_selector);

        for(int i = 0; i < Data.confrences.size(); i++){
            for(String s : Data.userSelectedTags){
                for(String tag : Data.tags.get(i).tags){
                    if(tag.equalsIgnoreCase(s) && !possibleTalks.contains(i)){
                        possibleTalks.add(i);
                    }
                }
            }
        }

        int tempViewID;
        int prevIndex;
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        final int width = (dm.widthPixels - 120) / 3;

        final ConstraintLayout layout = findViewById(R.id.ProgramaticConstraintLayout);

        TextView text1 = new TextView(getApplicationContext());
        text1.setId(View.generateViewId());
        text1.setText(Data.confrences.get(possibleTalks.get(0)).timeInString());
        text1.setTextSize(25);
        text1.setPadding(20, 20, 20, 20);
        text1.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(text1);
        ConstraintSet set1 = new ConstraintSet();
        set1.clone(layout);
        set1.connect(text1.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 400);
        set1.connect(text1.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
        tempViewID = text1.getId();
        set1.applyTo(layout);

        TextView text2 = new TextView(getApplicationContext());
        text2.setText(Data.confrences.get(possibleTalks.get(0)).title);
        text2.setId(View.generateViewId());
        text2.setBackgroundResource(R.color.lightRed);
        text2.setTextSize(20);
        text2.setPadding(20, 20, 20, 20);
        text2.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        final int temp2 = 0;
        final int tmpid2 = text2.getId();
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                for (int i = 0; i < talkIndexes.size(); i++) {
                    if (!flag && talkIndexes.get(i) == temp2) {
                        System.out.println(temp2 + " deactivate");
                        talkIndexes.remove(i);
                        layout.getViewById(tmpid2).setBackgroundResource(R.color.lightRed);
                        flag = true;
                    }
                }
                boolean overlap = false;
                for (int i : talkIndexes){
                    if(Data.isConfrenceOverLapping(possibleTalks.get(temp2)<possibleTalks.get(i)?possibleTalks.get(temp2):possibleTalks.get(i),possibleTalks.get(temp2)>=possibleTalks.get(i)?possibleTalks.get(temp2):possibleTalks.get(i))){
                        System.out.println("OVERLAP");
                        Toast.makeText(TalkSelector.this, "Talk timing conflict, choose another talk", Toast.LENGTH_SHORT).show();
                        overlap = true;
                    }
                }
                if (!flag && !overlap) {
                    System.out.println(temp2 + " activate");
                    talkIndexes.add(temp2);
                    layout.getViewById(tmpid2).setBackgroundResource(R.color.lightGreen);
                }

            }
        });
        layout.addView(text2);
        ConstraintSet set2 = new ConstraintSet();
        set2.clone(layout);
        set2.connect(text2.getId(), ConstraintSet.TOP, tempViewID, ConstraintSet.BOTTOM, 30);
        set2.connect(text2.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
        tempViewID = text2.getId();
        set2.applyTo(layout);

        for (int i = 1; i < possibleTalks.size(); i++) {
            if (!Data.confrences.get(possibleTalks.get(i-1)).startTime.equalsIgnoreCase(Data.confrences.get(possibleTalks.get(i)).startTime)) {
                TextView text = new TextView(getApplicationContext());
                text.setText(Data.confrences.get(possibleTalks.get(i)).timeInString());
                text.setId(View.generateViewId());
                text.setTextSize(25);
                text.setPadding(20, 20, 20, 20);
                text.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.addView(text);
                ConstraintSet set = new ConstraintSet();
                set.clone(layout);
                set.connect(text.getId(), ConstraintSet.TOP, tempViewID, ConstraintSet.BOTTOM, 30);
                set.connect(text.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
                tempViewID = text.getId();
                set.applyTo(layout);
            }
            TextView text = new TextView(getApplicationContext());
            text.setText(Data.confrences.get(possibleTalks.get(i)).title);
            text.setId(View.generateViewId());
            text.setBackgroundResource(R.color.lightRed);
            text.setTextSize(20);
            text.setPadding(20, 20, 20, 20);
            text.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final int temp = i;
            final int tmpid = text.getId();
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean flag = false;
                    for (int i = 0; i < talkIndexes.size(); i++) {
                        if (!flag && talkIndexes.get(i) == temp) {
                            System.out.println(temp + " deactivate");
                            talkIndexes.remove(i);
                            layout.getViewById(tmpid).setBackgroundResource(R.color.lightRed);
                            flag = true;
                        }
                    }
                    boolean overlap = false;
                    for (int i : talkIndexes){
                        if(Data.isConfrenceOverLapping(possibleTalks.get(temp)<possibleTalks.get(i)?possibleTalks.get(temp):possibleTalks.get(i) ,possibleTalks.get(temp)>=possibleTalks.get(i)?possibleTalks.get(temp):possibleTalks.get(i))){
                            System.out.println("OVERLAP");
                            Toast.makeText(TalkSelector.this, "Talk timing conflict, choose another talk", Toast.LENGTH_SHORT).show();
                            overlap = true;
                        }
                    }
                    if (!flag && !overlap) {
                        System.out.println(temp + " activate");
                        talkIndexes.add(temp);
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
        FloatingActionButton fab = findViewById(R.id.FABdone3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (talkIndexes.size() > 0) {
                    for(int i : talkIndexes){
                        Data.userSelectedTalks.add(i);
                    }
                    Intent i = new Intent(getApplicationContext(), Itinerary.class);
                    startActivity(i);
                }
            }
        });
    }
}
