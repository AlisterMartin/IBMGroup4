package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Infomation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talk_info);
        Data.initData(getApplicationContext());
    }
}
