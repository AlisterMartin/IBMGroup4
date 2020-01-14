package com.ibm.watson.developer_cloud.android.myapplication;

import android.widget.TextView;

public class ChatBoxes {

    private int count;
    private TextView[] boxes;
    private boolean vis;


    public ChatBoxes(int count, TextView[] boxes, boolean vis)
    {
        count = 0;
        boxes = null;
        vis = false;

    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public void setBoxes(TextView[] boxes)
    {
        this.boxes = boxes;
    }

    public void setVis(boolean vis)
    {
        this.vis = vis;
    }

    public int getCount()
    {
        return count;
    }

    public TextView[] getBoxes()
    {
        return boxes;
    }

    public boolean getVis()
    {
        return vis;
    }

}
