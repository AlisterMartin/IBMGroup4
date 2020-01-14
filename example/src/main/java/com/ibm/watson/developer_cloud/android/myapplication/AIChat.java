package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AIChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_chat);

        final StartChat chatBarView;

        TextView input1 = (TextView) findViewById(R.id.input1);
        TextView output1 = (TextView) findViewById(R.id.output1);
        TextView input2 = (TextView) findViewById(R.id.input2);
        TextView output2 = (TextView) findViewById(R.id.output2);
        TextView input3 = (TextView) findViewById(R.id.input3);
        TextView output3 = (TextView) findViewById(R.id.output3);
        TextView input4 = (TextView) findViewById(R.id.input4);

        TextView[] ChatBoxes = {input1,output1, input2, output2, input3, output3, input4};

        ChatBoxes cb = new ChatBoxes(7, ChatBoxes, false);

        chatBarView = (StartChat) findViewById(R.id.ChatBar);

        chatBarView.setSendClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animPopUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.message_pop_up);
                TextView input1 = (TextView) findViewById(R.id.input1);
                input1.setVisibility(View.VISIBLE);
                input1.startAnimation(animPopUp);
                input1.setText(chatBarView.getMessageText());

            }
        });

        if (input1.getText() != null)
        {
            input1.setVisibility(View.VISIBLE);

        }
    }
}
