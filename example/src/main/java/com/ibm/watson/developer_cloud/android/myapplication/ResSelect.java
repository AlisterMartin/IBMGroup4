package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ResSelect extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.res_select);

        TextView text1 = new TextView(getApplicationContext());
        text1.setText("");
        text1.setId(View.generateViewId());
        text1.setBackgroundResource(R.color.lightRed);
        text1.setTextSize(20);
        text1.setPadding(20, 20, 20, 20);
        text1.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

    }
}
