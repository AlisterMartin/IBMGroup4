package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class AIChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_chat);

        final StartChat chatBarView;

        ChatBoxes cb = new ChatBoxes();
        cb.addAssistantBox("hello", getApplicationContext(), (ConstraintLayout) findViewById(R.id.Constraint));

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
    }
}
