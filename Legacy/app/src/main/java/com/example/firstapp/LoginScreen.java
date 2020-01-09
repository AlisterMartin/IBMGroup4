package com.example.firstapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;

public class LoginScreen extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.usernameInput);
                if(username.getText().toString().equalsIgnoreCase("a")){
                    System.out.println("Correct username");
                }else{
                    System.out.println("Incorrect username");
                }

                startActivity(new Intent(LoginScreen.this, Welcome.class));
            }
        });

    }
}
