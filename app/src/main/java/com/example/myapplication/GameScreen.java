package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;


public class GameScreen extends AppCompatActivity {

    private TextView gameScreenName;
    private ImageView character;

    private TextView difficultyAndNumLives;

    private Player mainPlayer = new Player();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // Get a reference to your custom view (not sure about id.gameview)
        GameView gameView = findViewById(R.id.gameView);

        // Set the custom view to be focusable in touch mode and request focus
        gameView.setFocusableInTouchMode(true);
        gameView.requestFocus();

        gameScreenName = (TextView) findViewById(R.id.nameGameScreen);
        character = (ImageView) findViewById(R.id.sprite);
        difficultyAndNumLives = (TextView) findViewById(R.id.difficultyAndNumLives);


        String userName = getIntent().getStringExtra(ConfigPage.NAME_ID);
        String difficultyLevel = getIntent().getStringExtra("difficulty");
        int numLives = getIntent().getIntExtra("numLives", 0);

        gameScreenName.setText(String.format("Name: %s", userName));
        difficultyAndNumLives.setText(
                String.format("Difficulty: %s | Num Lives: %d", difficultyLevel, numLives)
        );

        mainPlayer.setCharImageDisplay(character);

        mainPlayer.setxPos(100);
        mainPlayer.setyPos(100);

//        character.setImageResource(getIntent().getIntExtra("image", 0));

        (mainPlayer.getCharImageDisplay()).setImageResource(getIntent().getIntExtra("image", 0));

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                // Move the character left
                mainPlayer.setxPos(mainPlayer.getxPos() - 10);
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                // Move the character right
                mainPlayer.setxPos(mainPlayer.getxPos() + 10);
                return true;
            case KeyEvent.KEYCODE_DPAD_UP:
                // Move the character up
                mainPlayer.setyPos(mainPlayer.getyPos() + 10);
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                // Move the character down
                mainPlayer.setyPos(mainPlayer.getyPos() - 10);
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

}