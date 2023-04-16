package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
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

    private static ImageView stick;
    private static ImageView log1;
    private static ImageView log2;
    private TextView difficultyAndNumLives;

    private int width;
    private int bottomHeightBounds;
    private Vehicle vehicleOne;
    private Vehicle vehicleTwo;
    private Vehicle vehicleThree;
    private int upperHeightBounds;
    private boolean positionInitialized = false;

    //finishing flag
    private static EndScreen.Flag mFlag;

    //0: Goal Tile, 1: River tile, 2: Safe tile, 3: Road Tile, 4: Start Tile
    private int[] gameMap = {0, 1, 1, 1, 2, 3, 3, 3, 4};
    private int[] widths = {200, 200, 300, 150, 20};
    private Tile[] gameTiles;
    private Tile[] bridgeTiles;

    private int mapUpperPosition = 200;

    private ImageView[] vehicleList = new ImageView[3];


    private Player gameCharacter;

    private int score = 0;
    private int minYPos = 20000;

    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int updateTimePeriod = 300;

    private int bridgeWidth = 400;
    private int bridgeStart = 100;

    private boolean endGameScreenDisplayed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);




        gameCharacter = (Player) getIntent().getSerializableExtra("playerObject");
        Rect characterRect = new Rect();
        gameScreenName = (TextView) findViewById(R.id.nameGameScreen);
        character = (ImageView) findViewById(R.id.sprite);
        difficultyAndNumLives = (TextView) findViewById(R.id.difficultyAndNumLives);
        character.getHitRect(characterRect);
        gameCharacter.setPlayerRect(characterRect);
        gameTiles = new Tile[gameMap.length];
        drawTiles();

        vehicle1 = (ImageView) findViewById(R.id.vehicle1);
        vehicle2 = (ImageView) findViewById(R.id.vehicle2);
        vehicle3 = (ImageView) findViewById(R.id.vehicle3);
        vehicleOne = new Vehicle(0, vehicle1.getId(), "Big");
        vehicleTwo = new Vehicle(0, vehicle2.getId(), "Small");
        vehicleThree = new Vehicle(0, vehicle3.getId(), "Medium");

        vehicleList[0] = vehicle1;
        vehicleList[1] = vehicle2;
        vehicleList[2] = vehicle3;

        stick = (ImageView) findViewById(R.id.stick);
        log1 = (ImageView) findViewById(R.id.log1);
        log2 = (ImageView) findViewById(R.id.log2);


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
        // gameCharacter.setPosition((int) character.getX(), (int) character.getY());
        // gameCharacter.setStartPosX(gameCharacter.getPosX());
        // gameCharacter.setStartPosY(gameCharacter.getPosY());

        character.setZ(1);
        moveVehicles();

        moveLogs();

        mFlag = new EndScreen.Flag();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                checkAndHandleCollisions(character, vehicleList);
                if (!endGameScreenDisplayed) {
                    handler.postDelayed(this, updateTimePeriod);
                }
            }
        }, updateTimePeriod);
    }

    public void checkAndHandleCollisions(ImageView character, ImageView[] vehicles) {
        checkRiverCollision(character);
        if (gameCharacter.isInCollision()) {
            gameCharacter.riverCollisionPenalty();
            killCharacter();
        }
        Rect playerRect = new Rect();
        character.getHitRect(playerRect);
        gameCharacter.setPlayerRect(playerRect);

        checkVehicleCollision(character, vehicleList);

        if (gameCharacter.isInCollision()) {
            gameCharacter.addVehicleCollisionPenalty();
            killCharacter();
        }

        if ((gameCharacter.getNumLives() < 0
                || gameCharacter.isGoal(getPositionFromIndex(0) + widths[0] - 50))
                && !endGameScreenDisplayed) {
            endGameScreenDisplayed = true;
            gameCharacter.setDead();
            Intent intentScore = new Intent(GameScreen.this, EndScreen.class);
            //game over no bonus
            intentScore.putExtra("WINORLOSE", 0);
            intentScore.putExtra("SAVED_SCORE", score);
            startActivity(intentScore);
        }

    }

    public static EndScreen.Flag getmFlag() {
        return mFlag;
    }

    public static void setmFlag(EndScreen.Flag mFlag) {
        GameScreen.mFlag = mFlag;
    }

    public boolean checkRiverCollision(ImageView character) {
        if (character != null) {
            Rect characterRect = new Rect();
            character.getHitRect(characterRect);
            for (int i = 0; i < gameMap.length; i++) {
                if (gameMap[i] == 1) {
                    boolean onBridge = false;
                    Rect riverTileRect = gameTiles[i].getRect();
                    for (int j = 0; j < bridgeTiles.length; j++) {
                        if (gameCharacter.isCollidingWithPlayer(bridgeTiles[j].getRect())) {
                            onBridge = true;
                            break;
                        }
                    }
                    if (onBridge) {
                        break;
                    }
                    if (gameCharacter.isCollidingWithPlayer(riverTileRect)) {
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
            for (ImageView vehicle : vehicles) {
                if (vehicle != null) { // add null check

                    Rect vehicleRect = new Rect();
                    vehicle.getHitRect(vehicleRect);


                    //System.out.println("characterRect: " + characterRect.toShortString());
                    //System.out.println("vehicleRect: " + vehicleRect.toShortString());
                    if (gameCharacter.isCollidingWithPlayer(vehicleRect)) {
                        gameCharacter.setInCollision(true);
                        return true; // Collision detected
                    }
                }
            }
        }
        return false; // No collision detected
    }


    //Moves character back to initial position, and subtracts life by one.
    private void killCharacter() {
        gameCharacter.setPosition(gameCharacter.getStartPosX(), gameCharacter.getStartPosY());
        character.setX(gameCharacter.getPosX());
        character.setY(gameCharacter.getPosY());
        gameCharacter.setInCollision(false);
        gameCharacter.decrementLives();
        TextView difficultyAndNumLives = findViewById(R.id.difficultyAndNumLives);
        String difficultyLevel = gameCharacter.getDifficulty();
        int numLives = gameCharacter.getNumLives();
        difficultyAndNumLives.setText(
                String.format("Difficulty: %s | Num Lives: %d", difficultyLevel, numLives)
        );

        TextView scoreView = findViewById(R.id.scoreText);
        scoreView.setText("Score: " + gameCharacter.getScore());
    }


    //Draws the tiles on the screen based on the game map array, and returns the startTile:
    private void drawTiles() {
        int bridgeTileCount = 0;
        for (int i = 0; i < gameMap.length; i++) {
            if (gameMap[i] == 1) {
                bridgeTileCount += 1;
            }
        }
        bridgeTiles = new Tile[bridgeTileCount];
        bridgeTileCount = 0;

        for (int i = 0; i < gameMap.length; i++) {
            Tile tile = null;
            Tile bridgeTile = null;
            //0: Goal Tile, 1: River tile, 2: Safe tile, 3: Road Tile, 4: Start Tile
            switch (gameMap[i]) {
            case 0:
                tile = new GoalTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                gameCharacter.setBoundsTop(getPositionFromIndex(i));
                break;
            case 1:
                tile = new RiverTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                bridgeTile =  new BridgeTile(this, bridgeStart,
                        getPositionFromIndex(i), widths[gameMap[i]], bridgeWidth, null);
                break;
            case 2:
                tile = new NewSafeTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                break;
            case 3:
                tile = new RoadTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                break;
            case 4:
                tile = new StartTile(this, 0, getPositionFromIndex(i), widths[gameMap[i]], null);
                int startPositionX = SCREEN_WIDTH / 2;
                int startPositionY = getPositionFromIndex(i) + 50;
                gameCharacter.setStartPosX(startPositionX);
                gameCharacter.setStartPosY(startPositionY);
                gameCharacter.setPosition(startPositionX, startPositionY);

                break;
            default:
                break;
            }
            gameTiles[i] = tile;
            ConstraintLayout layout = findViewById(R.id.game_screen_layout);
            layout.addView(tile);
            if (bridgeTile != null) {
                layout.addView(bridgeTile);
                bridgeTiles[bridgeTileCount++] =  bridgeTile;
            }

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

    private void moveLogs() {
        ValueAnimator animator1 = ValueAnimator.ofFloat(SCREEN_WIDTH, -475);
        moveLog(animator1, 7500, 175, log1);
        log1.bringToFront();

        ValueAnimator animator2 = ValueAnimator.ofFloat(-375, SCREEN_WIDTH);
        moveLog(animator2, 8500, 550, stick);
        stick.bringToFront();

        ValueAnimator animator3 = ValueAnimator.ofFloat(SCREEN_WIDTH, -375);
        moveLog(animator3, 6500, 750, log2);
        log2.bringToFront();



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
            gameCharacter.setBoundsDown(screenBottomPoint - widths[4],
                    widths[4], character.getHeight());
        }
        if (!gameCharacter.isDead()) {
            if (keycode == KeyEvent.KEYCODE_W) {
                moveUp();
                checkAndHandleCollisions(character, vehicleList);
                return true;
            } else if (keycode == KeyEvent.KEYCODE_S) {
                moveDown();
                checkAndHandleCollisions(character, vehicleList);
                return true;
            } else if (keycode == KeyEvent.KEYCODE_D) {
                moveRight();
                checkAndHandleCollisions(character, vehicleList);
                return true;
            } else if (keycode == KeyEvent.KEYCODE_A) {
                moveLeft();
                checkAndHandleCollisions(character, vehicleList);
                return true;
            }
        }
        return false;
    }

    public void moveUp() {
        gameCharacter.moveUp(vehicleOne, vehicleTwo, vehicleThree);
        character.setY(gameCharacter.getPosY());
        score = gameCharacter.getScore();
        TextView scoreView = findViewById(R.id.scoreText);
        scoreView.setText("Score: " + score);
        //Goal
        if (gameCharacter.isGoal(getPositionFromIndex(1))) {
            Intent intentScore = new Intent(GameScreen.this, EndScreen.class);
            //win bonus score
            score += 1000;
            intentScore.putExtra("WINORLOSE", 1);
            intentScore.putExtra("SAVED_SCORE", score);
            startActivity(intentScore);
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

    public void moveLog(ValueAnimator valueAnimator,
                            int duration,
                            int position, ImageView log) {
        valueAnimator.setDuration(duration);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                log.setY(position);
                float x = (float) animation.getAnimatedValue();
                log.setX(x);
                if (x < -log.getWidth()) {
                    log.setX(GameScreen.SCREEN_WIDTH);
                }
            }
        });


    }

    //restart
    @Override
    public void onRestart() {
        super.onRestart();
        if (mFlag.getFinishFlg()) {
            finish();
        }
    }

}