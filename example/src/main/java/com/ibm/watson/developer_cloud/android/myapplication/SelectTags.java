package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SelectTags extends AppCompatActivity {
    private int selectedIndex1, selectedIndex2, selectedIndex3, nextSelect = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int tempViewID;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_tags);
        ArrayList<String> tags = Data.getUniqueTags();
        ConstraintLayout layout = findViewById(R.id.innerLayout);
        TextView text1 = new TextView(getApplicationContext());
        text1.setText(tags.get(0));
        text1.setId(View.generateViewId());
        tempViewID = text1.getId();
        text1.setBackgroundResource(R.color.lightRed);
        text1.setTextSize(20);
        text1.setPadding(20, 20, 20, 20);
        text1.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(nextSelect){
                    case 1 : selectedIndex1 = 0;
                        break;
                    case 2 : selectedIndex2 = 0;
                        break;
                    default: selectedIndex3 = 0;
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
            text.setText(tags.get(0));
            text.setId(View.generateViewId());
            text.setBackgroundResource(R.color.lightRed);
            text.setTextSize(20);
            text.setPadding(20, 20, 20, 20);
            text.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            final int temp = i;
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(nextSelect){
                        case 1 : selectedIndex1 = temp;
                            break;
                        case 2 : selectedIndex2 = temp;
                            break;
                        default: selectedIndex3 = temp;
                    }
                }
            });
            layout.addView(text);
            ConstraintSet set = new ConstraintSet();
            set.clone(layout);
            if(i%3 == 0){
                set.connect(text.getId(), ConstraintSet.TOP, tempViewID, ConstraintSet.BOTTOM, 30);
                set.connect(text.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
            }else{
                set.connect(text.getId(), ConstraintSet.TOP, tempViewID, ConstraintSet.TOP);
                set.connect(text.getId(), ConstraintSet.LEFT, tempViewID, ConstraintSet.RIGHT, 30);
            }
            tempViewID = text.getId();
            set.applyTo(layout);
        }
    }
}
