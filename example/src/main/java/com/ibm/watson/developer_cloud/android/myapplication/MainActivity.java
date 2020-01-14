/*
 * Copyright 2017 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.ibm.watson.developer_cloud.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.http.ServiceCallback;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;
import com.ibm.watson.assistant.v2.Assistant;

public class MainActivity extends AppCompatActivity {
    public String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = findViewById(R.id.gallery_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IamAuthenticator assistantAuthenticator = new IamAuthenticator(getString(R.string.assistant_apikey));
                final Assistant assistant = new Assistant("2020-01-10", assistantAuthenticator);
                assistant.setServiceUrl(getString(R.string.assistant_url));
                final MessageInput input = new MessageInput.Builder().messageType("text").text("Show me the way").build();

                CreateSessionOptions options = new CreateSessionOptions.Builder(getString(R.string.assistant_id)).build();
                assistant.createSession(options).enqueue(new ServiceCallback<SessionResponse>() {
                    @Override
                    public void onResponse(Response<SessionResponse> response) {
                        sessionId = response.getResult().getSessionId();
                        MessageOptions options1 = new MessageOptions.Builder(getString(R.string.assistant_id), sessionId).input(input).build();

                        assistant.message(options1).enqueue(new ServiceCallback<MessageResponse>() {
                            @Override
                            public void onResponse(Response<MessageResponse> response) {
                                System.out.println(response.getResult().getOutput());
                            }

                            @Override
                            public void onFailure(Exception e) {
                                System.out.println("Sorry there was an error with Watson Assistant. Error: " + e.toString());
                            }
                        });
                    }

                    @Override
                    public void onFailure(Exception e) {
                        sessionId = "-";
                        System.out.println("Sorry there was an error creating the session. Error: " + e.toString());
                    }
                });


            }
        });
        Button map = findViewById(R.id.camera_button);
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent map = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(map);
            }
        });
    }
}