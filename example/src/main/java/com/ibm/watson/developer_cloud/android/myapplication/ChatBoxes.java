package com.ibm.watson.developer_cloud.android.myapplication;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class ChatBoxes {

    private int count;
    private int pointer;
    private TextView[] boxes;
    private int vis;
    private int MAXBOXES;
    private boolean waiting;
    private int maxWidth;


    public ChatBoxes(int maxWidth){
        MAXBOXES = 10;
        this.count = 0;
        boxes = new TextView[MAXBOXES];
        vis = 0;
        pointer = 0;
        waiting = false;
        this.maxWidth = maxWidth;
    }

    public ChatBoxes(int maxWidth, int max) {
        MAXBOXES = max;
        this.count = 0;
        boxes = new TextView[MAXBOXES];
        vis = 0;
        pointer = 0;
        waiting = false;
        this.maxWidth = maxWidth;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setBoxes(TextView[] boxes) {
        this.boxes = boxes;
    }

    public void setVis(int vis) {
        this.vis = vis;
        for (int i = 0; i < count; i++) {
            boxes[i].setVisibility(vis);
        }
    }

    public int getCount() {
        return count;
    }

    public TextView[] getBoxes() {
        return boxes;
    }

    public int getVis() {
        return vis;
    }

    private void initBox(String text, Context context, ConstraintLayout layout) {
        boxes[0] = new TextView(context);
        boxes[0].setText(text);
        boxes[0].setId(View.generateViewId());
        boxes[0].setBackgroundResource(R.drawable.recieve_message);
        boxes[0].setTextSize(18);
        boxes[0].setPadding(20, 20, 20, 20);
        boxes[pointer].setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(boxes[0]);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        set.connect(boxes[0].getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM, 200);
        set.connect(boxes[0].getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
        set.constrainMaxWidth(boxes[0].getId(), maxWidth);
        set.applyTo(layout);
        count++;
        pointer++;
    }

    public void addUserBox(String text, Context context, ConstraintLayout layout) {
        if (!waiting) {
            if (count > 0) {
                if (boxes[pointer] != null) {
                    layout.removeView(boxes[pointer]);
                }
                boxes[pointer] = new TextView(context);
                boxes[pointer].setText(text);
                boxes[pointer].setId(View.generateViewId());
                boxes[pointer].setBackgroundResource(R.drawable.sent_message);
                boxes[pointer].setTextSize(18);
                boxes[pointer].setPadding(20, 20, 20, 20);
                boxes[pointer].setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.addView(boxes[pointer]);
                ConstraintSet set = new ConstraintSet();
                set.clone(layout);
                set.connect(boxes[pointer].getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM, 200);
                set.connect(boxes[pointer].getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT, 30);
                set.connect(boxes[(pointer + MAXBOXES - 1) % MAXBOXES].getId(), ConstraintSet.BOTTOM, boxes[pointer].getId(), ConstraintSet.TOP, 30);
                set.constrainMaxWidth(boxes[pointer].getId(), maxWidth);
                set.applyTo(layout);
                int i = count < MAXBOXES ? count++ : count;
                pointer++;
                pointer = pointer % MAXBOXES;
            } else {
                initBox(text, context, layout);
            }
        }
    }

    public void addAssistantBox(String text, Context context, ConstraintLayout layout) {
        if (count > 0) {
            if (boxes[pointer] != null) {
                layout.removeView(boxes[pointer]);
            }
            boxes[pointer] = new TextView(context);
            boxes[pointer].setText(text);
            boxes[pointer].setId(View.generateViewId());
            boxes[pointer].setBackgroundResource(R.drawable.recieve_message);
            boxes[pointer].setTextSize(18);
            boxes[pointer].setPadding(20, 20, 20, 20);
            boxes[pointer].setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.addView(boxes[pointer]);
            ConstraintSet set = new ConstraintSet();
            set.clone(layout);
            set.connect(boxes[pointer].getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM, 200);
            set.connect(boxes[pointer].getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
            set.connect(boxes[(pointer + MAXBOXES - 1) % MAXBOXES].getId(), ConstraintSet.BOTTOM, boxes[pointer].getId(), ConstraintSet.TOP, 30);
            set.constrainMaxWidth(boxes[pointer].getId(), maxWidth);
            set.applyTo(layout);
            count = count < MAXBOXES ? count + 1 : MAXBOXES;
            pointer++;
            pointer = pointer % MAXBOXES;
            waiting = false;
            System.out.println(pointer);
            System.out.println(count);
        } else {
            initBox(text, context, layout);
            waiting = false;
        }
    }

    public void addAssistantTyping(Context context, ConstraintLayout layout) {
        if (!waiting) {
            waiting = true;
            if (boxes[pointer] != null) {
                layout.removeView(boxes[pointer]);
            }
            boxes[pointer] = new TextView(context);
            boxes[pointer].setText("...");
            boxes[pointer].setId(View.generateViewId());
            boxes[pointer].setBackgroundResource(R.drawable.recieve_message);
            boxes[pointer].setTextSize(18);
            boxes[pointer].setPadding(20, 20, 20, 20);
            boxes[pointer].setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.addView(boxes[pointer]);
            ConstraintSet set = new ConstraintSet();
            set.clone(layout);
            set.connect(boxes[pointer].getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM, 200);
            set.connect(boxes[pointer].getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 30);
            set.connect(boxes[(pointer + MAXBOXES - 1) % MAXBOXES].getId(), ConstraintSet.BOTTOM, boxes[pointer].getId(), ConstraintSet.TOP, 30);
            set.constrainMaxWidth(boxes[pointer].getId(), maxWidth);
            set.applyTo(layout);
        }
    }

}
