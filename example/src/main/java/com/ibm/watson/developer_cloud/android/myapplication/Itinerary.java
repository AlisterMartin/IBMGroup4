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

public class Itinerary extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.select_itineray);


        int tempViewID;
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);

        final ConstraintLayout layout = findViewById(R.id.ProgrammaticLayout1);

        TextView text1 = new TextView(getApplicationContext());
        text1.setId(View.generateViewId());
        text1.setText(Data.confrences.get(Data.userSelectedTalks.get(0)).timeInString());
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
        text2.setText(Data.confrences.get(Data.userSelectedTalks.get(0)).title);
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
                Intent i = new Intent(getApplicationContext(), TalkInfo.class);
                i.putExtra("selectedIndex", Data.userSelectedTalks.get(temp2));
                startActivity(i);
            }
        });
        layout.addView(text2);
        ConstraintSet set2 = new ConstraintSet();
        set2.clone(layout);
        set2.connect(text2.getId(), ConstraintSet.TOP, tempViewID, ConstraintSet.BOTTOM, 30);
        set2.connect(text2.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
        tempViewID = text2.getId();
        set2.applyTo(layout);

        for (int i = 1; i < Data.userSelectedTalks.size(); i++) {
            if (!Data.confrences.get(Data.userSelectedTalks.get(i-1)).startTime.equalsIgnoreCase(Data.confrences.get(Data.userSelectedTalks.get(i)).startTime)) {
                TextView text = new TextView(getApplicationContext());
                text.setText(Data.confrences.get(Data.userSelectedTalks.get(i)).timeInString());
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
                System.out.println("i");
            }
            TextView text = new TextView(getApplicationContext());
            text.setText(Data.confrences.get(Data.userSelectedTalks.get(i)).title);
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
                    Intent i = new Intent(getApplicationContext(), TalkInfo.class);
                    i.putExtra("selectedIndex", Data.userSelectedTalks.get(temp));
                    startActivity(i);
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
