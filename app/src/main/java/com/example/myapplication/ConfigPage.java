package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfigPage extends AppCompatActivity {
    private Button game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_page);
        setUpConfigPage(savedInstanceState);
    }

    public void setUpConfigPage(Bundle savedInstanceState) {

        game = (Button) findViewById(R.id.goToGame);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigPage.this, GameScreen.class);
                startActivity(intent);
            }
        });
    }
}