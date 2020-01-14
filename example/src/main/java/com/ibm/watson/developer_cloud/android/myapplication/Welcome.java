package com.ibm.watson.developer_cloud.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        final StartChat chatBarView;

        TextView Text1 = (TextView)findViewById(R.id.message1);
        User u1 = (User)getIntent().getSerializableExtra("user");
        Text1.setText(u1.getUserName() + ", Welcome to your Personalised Conference Assistant.");

        Animation animSlideLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_left);
        Text1.startAnimation(animSlideLeft);

        TextView Text2 = (TextView)findViewById(R.id.message2);
        Animation animSlideLeftDelay = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_left_delay);
        Text2.startAnimation(animSlideLeftDelay);

        FloatingActionButton tick = (FloatingActionButton) findViewById(R.id.FABWel);
                tick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), AIChat.class);
                        startActivity(intent);

                    }
                });

            }
        }


