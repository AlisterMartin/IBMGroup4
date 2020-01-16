package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TalkInfo extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.talk_info);

        int selectedIndex = (Integer) getIntent().getSerializableExtra("selectedIndex");


        TextView title = findViewById(R.id.title);
        title.setText(Data.confrences.get(selectedIndex).title);

        TextView time = findViewById(R.id.time);
        time.setText(Data.confrences.get(selectedIndex).timeInString());

        TextView room = findViewById(R.id.room);
        room.setText(Data.confrences.get(selectedIndex).location);

        TextView abs = findViewById(R.id.abs1);
        abs.setText(Data.confrences.get(selectedIndex).description);

        TextView resource = findViewById(R.id.resource);
        resource.setText(Data.relavantReading(selectedIndex));
    }
}
