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

    private boolean positionInitialized= false;

    Player gameCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        gameCharacter = (Player) getIntent().getSerializableExtra("playerObject");
        gameScreenName = (TextView) findViewById(R.id.nameGameScreen);
        character = (ImageView) findViewById(R.id.sprite);
        difficultyAndNumLives = (TextView) findViewById(R.id.difficultyAndNumLives);

        String userName = getIntent().getStringExtra(ConfigPage.NAME_ID);
        //String difficultyLevel = getIntent().getStringExtra("difficulty");
        //int numLives = getIntent().getIntExtra("numLives", 0);


        String difficultyLevel = gameCharacter.getDifficulty();
        int numLives = gameCharacter.getNumLives();

        gameScreenName.setText(String.format("Name: %s", userName));
        difficultyAndNumLives.setText(
                String.format("Difficulty: %s | Num Lives: %d", difficultyLevel, numLives)
        );

        character.setImageResource(gameCharacter.getImageResourceId());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        View startTile = findViewById(R.id.startTile);

        int startTileYPos = (int)startTile.getY();
        int startTileHeight = startTile.getHeight();
        bottomHeightBounds = (startTileYPos + startTileHeight/2 - 50);
        gameCharacter.setPosition((int)character.getX(), (int)character.getY());

    }

    @Override
    public boolean onKeyUp(int keycode, KeyEvent keyEvent) {
        if(!positionInitialized) {
            positionInitialized = true;
            gameCharacter.setPosition((int)character.getX(), (int)character.getY());

            View startTile = findViewById(R.id.startTile);
            View goalTile = findViewById(R.id.goalTile);

            int startTileYPos = (int)startTile.getY();
            int startTileHeight = startTile.getHeight();
            int goalTileYPos = (int)goalTile.getY();

            gameCharacter.setBoundsDown(startTileYPos,startTileHeight, character.getWidth());
            gameCharacter.setBoundsLeft(10);
            gameCharacter.setBoundsRight(width, character.getWidth());
            gameCharacter.setBoundsTop(goalTileYPos);
        }
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


    public void updateBounds() {


        View goalTile = findViewById(R.id.goalTile);

        int goalTileYPos = (int)goalTile.getY();
        upperHeightBounds = (goalTileYPos);


    }

    public void moveUp() {
        gameCharacter.moveUp();
        character.setY(gameCharacter.getPosY());
    }
    public void moveDown() {
        gameCharacter.moveDown();
        character.setY(gameCharacter.getPosY());
    }

    public void moveRight() {
        gameCharacter.moveRight();
        character.setX(gameCharacter.getPosX());
    }

    public void moveLeft() {
        gameCharacter.moveLeft();
        character.setX(gameCharacter.getPosX());
    }

    public int getRandomNumber() {
        return 10;
    }

}