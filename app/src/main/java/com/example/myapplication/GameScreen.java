package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GameScreen extends AppCompatActivity {

    private TextView gameScreenName;
    private ImageView character;

    private ImageView vehicle1;
    private ImageView vehicle2;
    private ImageView vehicle3;

    private TextView difficultyAndNumLives;

    private int width;
    private int bottomHeightBounds;
    private int upperHeightBounds;

    private boolean positionInitialized = false;

    //0: Goal Tile, 1: River tile, 2: Safe tile, 3: Road Tile, 4: Start Tile
    private int[] gameMap = {0,1,1,1,2,3,3,3,4};
    private int[] widths = {200, 225, 200, 250, 200};
    private Tile[] gameTiles;

    private int mapUpperPosition = 500;


    private Player gameCharacter;

    private int score = 0;
    private int minYPos= 20000;


    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;


    private int updateTimePeriod = 50;


    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        gameTiles = new Tile[gameMap.length];
        drawTiles();

        gameCharacter = (Player) getIntent().getSerializableExtra("playerObject");
        gameScreenName = (TextView) findViewById(R.id.nameGameScreen);
        character = (ImageView) findViewById(R.id.sprite);
        difficultyAndNumLives = (TextView) findViewById(R.id.difficultyAndNumLives);

        vehicle1 = (ImageView) findViewById(R.id.vehicle1);
        vehicle2 = (ImageView) findViewById(R.id.vehicle2);
        vehicle3 = (ImageView) findViewById(R.id.vehicle3);

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


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(checkRiverCollision(character)) {
                    System.out.println("IN COLLISION!");
                }
                handler.postDelayed(this, updateTimePeriod);
            }
        }, updateTimePeriod);
    }


    public boolean checkRiverCollision(ImageView character) {
        if (character != null) {
            Rect characterRect = new Rect();
            character.getHitRect(characterRect);
            for(int i = 0; i < gameMap.length; i++) {
                if(gameMap[i] == 1) {
                    Rect riverTileRect = gameTiles[i].getRect();
                    if(characterRect.intersect(riverTileRect)) {
                        gameCharacter.setInCollision(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkVehicleCollision(ImageView character, ImageView[] vehicles) {
        if (character != null) {
            Rect characterRect = new Rect();
            character.getHitRect(characterRect);
            for (ImageView vehicle : vehicles) {
                if (vehicle != null) { // add null check
                    Rect vehicleRect = new Rect();
                    vehicle.getHitRect(vehicleRect);
//                    System.out.println("characterRect: " + characterRect.toShortString());
//                    System.out.println("vehicleRect: " + vehicleRect.toShortString());
                    if (characterRect.intersect(vehicleRect)) {
                        return true; // Collision detected
                    }



                }
            }

        }



        return false; // No collision detected
    }

    //Draws the tiles on the screen based on the game map array, and returns the startTile:
    private void drawTiles() {
        for(int i = 0; i < gameMap.length; i++) {
            Tile tile = null;

            //0: Goal Tile, 1: River tile, 2: Safe tile, 3: Road Tile, 4: Start Tile
            switch(gameMap[i]) {
                case 0:
                    tile = new GoalTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]],null);
                    break;
                case 1:
                    tile = new RiverTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]],null);
                    break;
                case 2:
                    tile = new NewSafeTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]],null);
                    break;
                case 3:
                    tile = new RoadTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]],null);
                    break;
                case 4:
                    tile = new StartTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]],null);
                    break;
            }
            gameTiles[i] = tile;
            ConstraintLayout layout = findViewById(R.id.game_screen_layout);
            layout.addView(tile);
            if(gameMap[i] == 0) {
                this.upperHeightBounds = getPositionFromIndex(i);
            } else if(gameMap[i] == 4) {
                this.bottomHeightBounds = getPositionFromIndex(i) + widths[4];
            }
            tile.setZ(0);
        }
    }

    //Gets the Y position (block top) of the tile based on the index of the array.
    private int getPositionFromIndex(int index) {
        int currentYPosition = mapUpperPosition;
        for(int i = 0; i < index; i++) {
            currentYPosition += widths[gameMap[i]];
        }
        if(gameMap[index] == 4) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenBottomPoint = displayMetrics.heightPixels;
            widths[4] = screenBottomPoint - currentYPosition;

        }
        return currentYPosition;
    }


    private void moveVehicles() {
        // Vehicle 1 moves from right to left
        ValueAnimator animator1 = ValueAnimator.ofFloat(screenWidth,-375);
        animator1.setDuration(5000);
        animator1.setRepeatMode(ValueAnimator.RESTART);
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                vehicle1.setY(getPositionFromIndex(7));

                float x = (float) animation.getAnimatedValue();
                vehicle1.setX(x);
                if (x < -vehicle1.getWidth()) {
                    vehicle1.setX(screenWidth);
                }
            }
        });

        // Vehicle 3 moves from right to left
        ValueAnimator animator3 = ValueAnimator.ofFloat(screenWidth, -375);

        animator3.setDuration(6500);
        animator3.setRepeatMode(ValueAnimator.RESTART);
        animator3.setRepeatCount(ValueAnimator.INFINITE);
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                vehicle3.setX(x);
                vehicle3.setY(getPositionFromIndex(5));

                if (x < -vehicle3.getRight()) {
                    vehicle3.setX(screenWidth);
                }
            }
        });

        // Vehicle 2 moves from left to right
        ValueAnimator animator2 = ValueAnimator.ofFloat(-375, screenWidth);

        animator2.setDuration(8000);
        animator2.setRepeatMode(ValueAnimator.RESTART);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                vehicle2.setX(x);
                vehicle2.setY(getPositionFromIndex(6));

                if (x >= screenWidth) {
                    vehicle2.setX(-vehicle2.getWidth());
                }
            }
        });

        animator1.start();
        animator2.start();
        animator3.start();

        vehicle1.bringToFront();
        vehicle2.bringToFront();
        vehicle3.bringToFront();

    }

    @Override
    public boolean onKeyUp(int keycode, KeyEvent keyEvent) {
        //character top,
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenBottomPoint = displayMetrics.heightPixels;
        System.out.println((gameCharacter.getPosY() + character.getHeight()) + " " + (screenBottomPoint));
        if (!positionInitialized) {
            positionInitialized = true;
            gameCharacter.setPosition((int) character.getX(), (int) character.getY());
            gameCharacter.setBoundsLeft(10);
            gameCharacter.setBoundsRight(width, character.getWidth());
            gameCharacter.setBoundsTop(this.upperHeightBounds);
            gameCharacter.setBoundsDown(screenBottomPoint - widths[4], widths[4], character.getHeight());
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
        gameCharacter.moveUp();
        character.setY(gameCharacter.getPosY());
        System.out.println(minYPos + " " + character.getY());
        if (character.getY() < minYPos) {
            minYPos = (int) character.getY();
            TextView scoreView = findViewById(R.id.scoreText);
            if (minYPos < 1250) {
                score += 20;
            } else if (minYPos < 1500) {
                score += 10;
            } else {
                score += 40;
            }
            scoreView.setText("Score: " + score);
        }
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