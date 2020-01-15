package com.ibm.watson.developer_cloud.android.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class ChoiceBoxes {

    private int count;
    private int pointer;
    private TextView[] boxRows;
    private int vis;
    private int MAXBOXES;
    private boolean waiting;


    public ChoiceBoxes() {
        MAXBOXES = 10;
        this.count = 0;
        boxRows = new TextView[MAXBOXES];
        vis = 0;
        pointer = 0;
        waiting = false;

    }

    public ChoiceBoxes(int max) {
        MAXBOXES = max;
        this.count = 0;
        boxRows = new TextView[MAXBOXES];
        vis = 0;
        pointer = 0;
        waiting = false;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setBoxes(TextView[] boxes) {
        this.boxRows = boxes;
    }

    public void setVis(int vis) {
        this.vis = vis;
        for (int i = 0; i < count; i++) {
            boxRows[i].setVisibility(vis);
        }
    }

    public int getCount() {
        return count;
    }

    public TextView[] getBoxes() {
        return boxRows;
    }

    public int getVis() {
        return vis;
    }


    private void displayRows(String text, Context context, ConstraintLayout layout) {

        if (!waiting) {
            if (count > 0) {
                if (boxRows[pointer] != null) {
                    layout.removeView(boxRows[pointer]);
                }
                boxRows[pointer] = new TextView(context);
                boxRows[pointer].setText(text);
                boxRows[pointer].setId(View.generateViewId());
                boxRows[pointer].setBackgroundResource(R.color.lightRed);
                boxRows[pointer].setTextSize(20);
                boxRows[pointer].setPadding(20, 20, 20, 20);
                boxRows[pointer].setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.addView(boxRows[pointer]);
                ConstraintSet set = new ConstraintSet();
                set.clone(layout);
                set.connect(boxRows[pointer].getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 200);
                set.connect(boxRows[pointer].getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 0);
                set.connect(boxRows[(pointer + MAXBOXES - 1) % MAXBOXES].getId(), ConstraintSet.BOTTOM, boxRows[pointer].getId(), ConstraintSet.TOP, 30);
                set.applyTo(layout);
                int i = count < MAXBOXES ? count++ : count;
                pointer++;
                pointer = pointer % MAXBOXES;
            } else {

            }
        }
    }
}
