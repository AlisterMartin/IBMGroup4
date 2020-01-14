package com.ibm.watson.developer_cloud.android.myapplication;

import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

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
