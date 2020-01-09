package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.mulButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText1 = findViewById(R.id.firstEdit);
                EditText editText2 = findViewById(R.id.secondEdit);
                TextView textView1 = findViewById(R.id.Product);
                int num1 = Integer.parseInt(editText1.getText().toString());
                int num2 = Integer.parseInt(editText2.getText().toString());
                int result = num1 * num2;
                textView1.setText(result + " ");

            }
        }
        );
    }

}
