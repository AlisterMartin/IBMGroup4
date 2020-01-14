package com.ibm.watson.developer_cloud.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.http.ServiceCallback;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;
import com.ibm.watson.assistant.v2.Assistant;

import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.appcompat.app.AppCompatActivity;
import static java.lang.Thread.sleep;

public class AIChat extends AppCompatActivity {
    private String sessionId;
    private boolean wait = false;
    private String tempResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_chat);


        IamAuthenticator assistantAuthenticator = new IamAuthenticator(getString(R.string.assistant_apikey));
        final Assistant assistant = new Assistant("2020-01-10", assistantAuthenticator);
        assistant.setServiceUrl(getString(R.string.assistant_url));

        CreateSessionOptions options = new CreateSessionOptions.Builder(getString(R.string.assistant_id)).build();

        final StartChat chatBarView;

        final ChatBoxes cb = new ChatBoxes();
        cb.addAssistantBox("Hello, I am Watson Assistant. Your conference planner.", getApplicationContext(), (ConstraintLayout) findViewById(R.id.Constraint));

        assistant.createSession(options).enqueue(new ServiceCallback<SessionResponse>() {
            @Override
            public void onResponse(Response<SessionResponse> response) {
                sessionId = response.getResult().getSessionId();
            }

            @Override
            public void onFailure(Exception e) {
                sessionId = "-";
                cb.addAssistantBox("Sorry there was an error creating the session. Error: " + e.toString(), getApplicationContext(), (ConstraintLayout) findViewById(R.id.Constraint));
            }
        });

        chatBarView = (StartChat) findViewById(R.id.ChatBar);

        chatBarView.setSendClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb.addUserBox(chatBarView.getMessageText(), getApplicationContext(), (ConstraintLayout) findViewById(R.id.Constraint));
                cb.addAssistantTyping(getApplicationContext(), (ConstraintLayout) findViewById(R.id.Constraint));
                MessageInput input = new MessageInput.Builder().messageType("text").text(chatBarView.getMessageText()).build();
                if (!sessionId.equalsIgnoreCase("-")){
                    MessageOptions options1 = new MessageOptions.Builder(getString(R.string.assistant_id), sessionId).input(input).build();
                    wait = true;
                    assistant.message(options1).enqueue(new ServiceCallback<MessageResponse>() {
                        @Override
                        public void onResponse(Response<MessageResponse> response) {
                            wait = false;
                            tempResponse = response.getResult().getOutput().getGeneric().get(0).text();
                        }

                        @Override
                        public void onFailure(Exception e) {
                            wait = false;
                            tempResponse = "Sorry there was an error with Watson Assistant. Error: " + e.toString();
                        }
                    });
                    while (wait){
                        try{
                            sleep(500);
                        }
                        catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    cb.addAssistantBox(tempResponse, getApplicationContext(), (ConstraintLayout) findViewById(R.id.Constraint));

                    if(tempResponse.equalsIgnoreCase("Here is your route to the conference.")){
                        try{
                            sleep(1000);
                        }
                        catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }
}
