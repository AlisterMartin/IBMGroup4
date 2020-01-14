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

        final ChatBoxes cb = new ChatBoxes();
        cb.addAssistantBox("hello", getApplicationContext(), (ConstraintLayout) findViewById(R.id.Constraint));

        chatBarView = (StartChat) findViewById(R.id.ChatBar);

        chatBarView.setSendClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cb.addUserBox(chatBarView.getMessageText(), getApplicationContext(), (ConstraintLayout) findViewById(R.id.Constraint));
                cb.addAssistantTyping(getApplicationContext(), (ConstraintLayout) findViewById(R.id.Constraint));

            }
        });
    }
}
