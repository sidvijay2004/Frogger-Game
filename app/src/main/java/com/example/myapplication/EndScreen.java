package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class EndScreen extends AppCompatActivity {
    private Button restart;
    private Button quitGame;
    private TextView endScreenTitle;
    private int winOrLose;
    private TextView finalScore;
    private int displayScore;

    //finishing flag
    public static class Flag{
        private boolean finishflg;
        public boolean getFinishFlg(){
            return finishflg;
        }
        public void setFinishFlg(boolean flg){
            finishflg = flg;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);
        setUpEndScreen(savedInstanceState);

        //gameCharacter = (Player) getIntent().getSerializableExtra("playerObject");

    }

    public void setUpEndScreen(Bundle savedInstanceState) {
        restart = (Button) findViewById(R.id.restartGameBttn);
        quitGame = (Button) findViewById(R.id.quitGameBttn);
        Intent intentScore = getIntent();
        displayScore = (int) getIntent().getSerializableExtra("SAVED_SCORE");
        winOrLose = (int) getIntent().getSerializableExtra("WINORLOSE");
        endScreenTitle = (TextView) findViewById(R.id.endScreenTitle);
        if (winOrLose == 1) {
            endScreenTitle.setText("Congrats!");
        }
        finalScore = (TextView) findViewById(R.id.finalScore);
        finalScore.setText(String.format("Score: %d", displayScore));
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndScreen.this, ConfigPage.class);
                startActivity(intent);
            }
        });

        quitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameScreen.mFlag.setFinishFlg(true);
                ConfigPage.mFlag.setFinishFlg(true);
                finish();
            }
        });
    }


}