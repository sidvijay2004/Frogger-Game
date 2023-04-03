package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        setUpWelcomePage(savedInstanceState);
    }


    public void setUpWelcomePage(Bundle savedInstanceState) {

        start = (Button) findViewById(R.id.startGameBttn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigPage.class);
                startActivity(intent);
                finish();
            }
        });
    }


}