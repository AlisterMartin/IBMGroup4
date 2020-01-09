package com.example.firstapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;

public class LoginScreen extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
<<<<<<< HEAD

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scanner sc = new Scanner(System.in);
                String userName =

                        EditText editText1 = findViewById(R.id.firstEdit);
                EditText editText2 = findViewById(R.id.secondEdit);
                TextView textView1 = findViewById(R.id.Product);
                int num1 = Integer.parseInt(editText1.getText().toString());
                int num2 = Integer.parseInt(editText2.getText().toString());
                int result = num1 * num2;
                textView1.setText(result + " ");

                startActivity(new Intent(LoginScreen.this, Welcome.class));
=======
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView username = findViewById(R.id.usernameInput);
                if(username.getText().toString().equalsIgnoreCase("a")){
                    System.out.println("Correct username");
                }else{
                    System.out.println("Incorrect username");
                }
>>>>>>> 02917a019525b9b7dbdf6d9c373b128eae59139b
            }
        });

    }
}
