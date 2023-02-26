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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

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

        character.setImageResource(getIntent().getIntExtra("image", 0));

    }

    @Override
    public boolean onKeyUp(int keycode, KeyEvent keyEvent) {
        if (keycode == KeyEvent.KEYCODE_W) {

            moveUp();
            return true;
        } else if (keycode == KeyEvent.KEYCODE_S) {
            moveDown();
            return true;
        } else if (keycode == KeyEvent.KEYCODE_D) {
            moveRight();
            return true;
        } else if (keycode == KeyEvent.KEYCODE_A) {
            moveLeft();
            return true;
        }
        return false;
    }

    public void moveUp() {
        if (character.getY() >= 650) {
            character.setY(character.getY() - 10);
        }
    }

    public void moveDown() {
        if (character.getY() <= 1940)
        character.setY(character.getY() + 10);
    }

    public void moveRight() {
        if (character.getX() <= 1000) {
            character.setX(character.getX() + 10);
        }
    }

    public void moveLeft() {
        if (character.getX() >= 10) {
            character.setX(character.getX() - 10);
        }
    }








}