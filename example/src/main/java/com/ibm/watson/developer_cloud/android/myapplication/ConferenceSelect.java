package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ConferenceSelect extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.conference_select);

        final TextView text1 = findViewById(R.id.con1);
        final TextView text2 = findViewById(R.id.con2);
        final TextView text3 = findViewById(R.id.con3);
        final TextView text4 = findViewById(R.id.con4);
        final TextView text5 = findViewById(R.id.con5);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setBackgroundResource(R.color.lightGreen);
                text2.setBackgroundResource(R.color.lightRed);
                text3.setBackgroundResource(R.color.lightRed);
                text4.setBackgroundResource(R.color.lightRed);
                text5.setBackgroundResource(R.color.lightRed);



            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setBackgroundResource(R.color.lightRed);
                text2.setBackgroundResource(R.color.lightGreen);
                text3.setBackgroundResource(R.color.lightRed);
                text4.setBackgroundResource(R.color.lightRed);
                text5.setBackgroundResource(R.color.lightRed);

            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setBackgroundResource(R.color.lightRed);
                text2.setBackgroundResource(R.color.lightRed);
                text3.setBackgroundResource(R.color.lightGreen);
                text4.setBackgroundResource(R.color.lightRed);
                text5.setBackgroundResource(R.color.lightRed);

            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setBackgroundResource(R.color.lightRed);
                text2.setBackgroundResource(R.color.lightRed);
                text3.setBackgroundResource(R.color.lightRed);
                text4.setBackgroundResource(R.color.lightGreen);
                text5.setBackgroundResource(R.color.lightRed);

            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setBackgroundResource(R.color.lightRed);
                text2.setBackgroundResource(R.color.lightRed);
                text3.setBackgroundResource(R.color.lightRed);
                text4.setBackgroundResource(R.color.lightRed);
                text5.setBackgroundResource(R.color.lightGreen);

            }
        });
    }
}
