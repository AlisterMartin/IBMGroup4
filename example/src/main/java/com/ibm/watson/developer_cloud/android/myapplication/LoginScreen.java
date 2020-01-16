package com.ibm.watson.developer_cloud.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        Data.initData(getApplicationContext());
        final Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                

                EditText username = findViewById(R.id.usernameInput);

                if (username.getText().toString().equalsIgnoreCase("")) {
                    System.out.println("no username provided");
                    TextView noUser = findViewById(R.id.badUser);
                    noUser.setText("* No Username Provided");
                    noUser.setVisibility(View.VISIBLE);
                } else {

                    if (username.getText().toString().equalsIgnoreCase("username")) {

                        System.out.println("Incorrect username");
                        TextView wrongUser = findViewById(R.id.badUser);
                        wrongUser.setText("* Incorrect Username");
                        wrongUser.setVisibility(View.VISIBLE);

                    } else {
                        System.out.println("Correct username");
                        TextView wrongUser = findViewById(R.id.badUser);
                        wrongUser.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(getApplicationContext(), Welcome.class);
                        User u1 = new User("");
                        intent.putExtra("user", u1);
                        String uName = username.getText().toString();
                        u1.setUserName(uName);
                        startActivity(intent);

                    }

                }
            }
        });

    }
}
