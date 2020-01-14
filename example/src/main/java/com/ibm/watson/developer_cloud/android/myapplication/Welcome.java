package com.ibm.watson.developer_cloud.android.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        final StartChat chatBarView;
        String text;
        Animation animPopUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.message_pop_up);


        TextView Text1 = (TextView)findViewById(R.id.message1);
        User u1 = (User)getIntent().getSerializableExtra("user");
        Text1.setText(u1.getUserName() + ", Welcome to your Personalised Conference Assistant.");

        Animation animSlideLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_left);
        Text1.startAnimation(animSlideLeft);

        TextView Text2 = (TextView)findViewById(R.id.message2);
        Animation animSlideLeftDelay = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_left_delay);
        Text2.startAnimation(animSlideLeftDelay);

        chatBarView = (StartChat) findViewById(R.id.ChatBar);

        chatBarView.setSendClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView inputMessage = (TextView) findViewById(R.id.message_body);
                inputMessage.setVisibility(View.VISIBLE);

                inputMessage.setText(chatBarView.getMessageText());

                if(chatBarView.getMessageText().equalsIgnoreCase("start"))
                {
                    Animation animPopUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.message_pop_up);
                    Animation animPopUpDelay = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.message_pop_up_delay);
                    inputMessage.startAnimation(animPopUp);

                    TextView outputMessage = (TextView) findViewById(R.id.message_recieve);
                    outputMessage.startAnimation(animPopUpDelay);
                    outputMessage.setText("Great!");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(getApplicationContext(), AIChat.class);
                    startActivity(intent);


                }
                else {
                    Animation animPopUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.message_pop_up);
                    Animation animPopUpDelay = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.message_pop_up_delay);
                    inputMessage.startAnimation(animPopUp);
                    TextView outputMessage = (TextView) findViewById(R.id.message_recieve);
                    outputMessage.startAnimation(animPopUpDelay);
                    outputMessage.setText("Type 'Start' to get started...");
                }


            }
        });

        text = chatBarView.getMessageText();


    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}
