package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class GameScreen extends AppCompatActivity {

    private TextView gameScreenName;
    private ImageView character;

    private TextView difficultyAndNumLives;

    private int width;
    private int bottomHeightBounds;
    private int upperHeightBounds;

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

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        View startTile = findViewById(R.id.startTile);

        int startTileYPos = (int)startTile.getY();
        int startTileHeight = startTile.getHeight();
        bottomHeightBounds = (startTileYPos + startTileHeight/2 - 50);


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
        character.setY(character.getY() - 10);
        updateBounds();
        if (character.getY() < upperHeightBounds) {
            character.setY(upperHeightBounds);
        }
    }
    public void updateBounds() {
        View startTile = findViewById(R.id.startTile);
        int startTileYPos = (int)startTile.getY();
        int startTileHeight = startTile.getHeight();
        bottomHeightBounds = (startTileYPos + startTileHeight/2 - 50);

        View goalTile = findViewById(R.id.goalTile);

        int goalTileYPos = (int)goalTile.getY();
        int goalTileHeight = goalTile.getHeight();
        upperHeightBounds = (goalTileYPos);


    }
    public void moveDown() {
        character.setY(character.getY() + 10);

        updateBounds();

        if(character.getY() > bottomHeightBounds) {
            character.setY(bottomHeightBounds);
        }
    }

    public void moveRight() {
        if (character.getX() <= width - (character.getWidth())) {
            character.setX(character.getX() + 10);
        }
    }

    public void moveLeft() {
        if (character.getX() >= 10) {
            character.setX(character.getX() - 10);
        }
    }








}