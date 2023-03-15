package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    private TextView gameScreenName;
    private ImageView character;

    private static ImageView vehicle1;
    private static ImageView vehicle2;
    private static ImageView vehicle3;

    private TextView difficultyAndNumLives;

    private int width;
    private int bottomHeightBounds;
    private Vehicle vehicleOne;
    private Vehicle vehicleTwo;
    private Vehicle vehicleThree;
    private int upperHeightBounds;

    private boolean positionInitialized = false;

    //0: Goal Tile, 1: River tile, 2: Safe tile, 3: Road Tile, 4: Start Tile
    private int[] gameMap = {0, 1, 1, 1, 2, 3, 3, 3, 4};
    private int[] widths = {200, 200, 150, 150, 20};
    private int mapUpperPosition = 500;


    private Player gameCharacter;

    private int score = 0;
    private int minYPos = 20000;

    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);



        drawTiles();

        gameCharacter = (Player) getIntent().getSerializableExtra("playerObject");
        gameScreenName = (TextView) findViewById(R.id.nameGameScreen);
        character = (ImageView) findViewById(R.id.sprite);
        difficultyAndNumLives = (TextView) findViewById(R.id.difficultyAndNumLives);

        vehicle1 = (ImageView) findViewById(R.id.vehicle1);
        vehicle2 = (ImageView) findViewById(R.id.vehicle2);
        vehicle3 = (ImageView) findViewById(R.id.vehicle3);
        vehicleOne = new Vehicle(0, vehicle1.getId(), "Big");
        vehicleTwo = new Vehicle(0, vehicle2.getId(), "Small");
        vehicleThree = new Vehicle(0, vehicle3.getId(), "Medium");

        String userName = getIntent().getStringExtra(ConfigPage.NAME_ID);


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
        gameCharacter.setPosition((int) character.getX(), (int) character.getY());
        character.setZ(1);
        moveVehicles();
    }

    //Draws the tiles on the screen based on the game map array, and returns the startTile:
    private void drawTiles() {
        for (int i = 0; i < gameMap.length; i++) {
            Tile tile = null;

            //0: Goal Tile, 1: River tile, 2: Safe tile, 3: Road Tile, 4: Start Tile
            switch (gameMap[i]) {
            case 0:
                tile = new GoalTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                break;
            case 1:
                tile = new RiverTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                break;
            case 2:
                tile = new NewSafeTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                break;
            case 3:
                tile = new RoadTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                break;
            case 4:
                tile = new StartTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                break;
            default:
                break;
            }
            ConstraintLayout layout = findViewById(R.id.game_screen_layout);
            layout.addView(tile);


        }
    }

    //Gets the Y position (block top) of the tile based on the index of the array.
    public int getPositionFromIndex(int index) {
        int currentYPosition = mapUpperPosition;
        for (int i = 0; i < index; i++) {
            currentYPosition += widths[gameMap[i]];
        }
        if (gameMap[index] == 4) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenBottomPoint = displayMetrics.heightPixels;
            widths[4] = screenBottomPoint - currentYPosition;

        }
        return currentYPosition;
    }


    private void moveVehicles() {
        gameCharacter.setVehicle1(vehicleOne);
        gameCharacter.setVehicle2(vehicleTwo);
        gameCharacter.setVehicle3(vehicleThree);
        ValueAnimator animator1 = ValueAnimator.ofFloat(SCREEN_WIDTH, -375);
        moveVehicle(gameCharacter.getVehicle1(),
                animator1, 5000, getPositionFromIndex(7), vehicle1);
        vehicle1.bringToFront();

        ValueAnimator animator3 = ValueAnimator.ofFloat(-375, SCREEN_WIDTH);
        moveVehicle(gameCharacter.getVehicle3(),
                animator3, 6500, getPositionFromIndex(5), vehicle2);
        vehicle3.bringToFront();


        ValueAnimator animator2 = ValueAnimator.ofFloat(SCREEN_WIDTH, -375);
        moveVehicle(gameCharacter.getVehicle2(),
                animator2, 8000, getPositionFromIndex(6), vehicle3);
        vehicle2.bringToFront();

        animator1.start();
        animator2.start();
        animator3.start();

    }

    @Override
    public boolean onKeyUp(int keycode, KeyEvent keyEvent) {
        //character top,
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenBottomPoint = displayMetrics.heightPixels;

        if (!positionInitialized) {
            positionInitialized = true;
            gameCharacter.setPosition((int) character.getX(), (int) character.getY());
            gameCharacter.setBoundsLeft(10);
            gameCharacter.setBoundsRight(width, character.getWidth());
            gameCharacter.setBoundsTop(this.upperHeightBounds);
            gameCharacter.setBoundsDown(screenBottomPoint - widths[4],
                    widths[4], character.getHeight());
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

    public void moveUp() {
        gameCharacter.moveUp(vehicleOne, vehicleTwo, vehicleThree);
        character.setY(gameCharacter.getPosY());
        score = gameCharacter.getScore();
        TextView scoreView = findViewById(R.id.scoreText);
        scoreView.setText("Score: " + score);

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

    public void moveVehicle(Vehicle vehicleObj,
                            ValueAnimator valueAnimator,
                            int duration,
                            int position, ImageView vehicle) {
        valueAnimator.setDuration(duration);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                vehicle.setY(position);
                vehicleObj.setPosY(position);
                float x = (float) animation.getAnimatedValue();
                vehicle.setX(x);
                vehicleObj.setPosX(x);
                if (x < -vehicle.getWidth()) {
                    vehicle.setX(GameScreen.SCREEN_WIDTH);
                }
            }
        });


    }





}