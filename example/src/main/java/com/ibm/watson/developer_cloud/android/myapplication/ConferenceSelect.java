package com.ibm.watson.developer_cloud.android.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ConferenceSelect extends AppCompatActivity {
    public String selectedText = "Chi Conference 2019";
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
                selectedText = text1.getText().toString();


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
                selectedText = text2.getText().toString();
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
                selectedText = text3.getText().toString();
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
                selectedText = text4.getText().toString();
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
                selectedText = text5.getText().toString();
            }
        });

        FloatingActionButton fab = findViewById(R.id.FABdone5);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("SelectedText",selectedText);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
