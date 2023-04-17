package com.example.myapplication;
import android.graphics.Rect;

import java.io.Serializable;

public class Player implements Serializable {
    private int numLives;

    private float posX;
    private float posY;

    private int boundsLeft;
    private int boundsRight;
    private int boundsUp;
    private int boundsDown;

    private int imageResource;

    private boolean playerOnLog;

    private String difficulty;
    private int score = 0;
    private int minYPos = 20000;


    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private Vehicle vehicle3;
    private boolean inCollision;
    private boolean isDead = false;

    private int gameWinStatus = -1;

    private boolean reachedGoal = false;

    public Rect getPlayerRect() {
        return playerRect;
    }

    public void setPlayerRect(Rect playerRect) {
        this.playerRect = new Rect(playerRect);
    }

    public int getGameWinStatus() {
        return gameWinStatus;
    }

    public boolean getReachedGoal() {
        return reachedGoal;
    }

    public void setReachedGoal(boolean b) {
        reachedGoal = b;
    }

    public void setGameWinStatus(int val) {
        gameWinStatus = val;
    }

    private Rect playerRect;

    public int getStartPosX() {
        return startPosX;
    }

    public void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
    }

    public int getStartPosY() {
        return startPosY;
    }

    public void setStartPosY(int startPosY) {
        this.startPosY = startPosY;
    }

    private int startPosX;
    private int startPosY;

    private boolean passedVehicle1 = false, passedVehicle2 = false, passedVehicle3 = false;

    public boolean isPlayerOnLog() {
        return playerOnLog;
    }

    public void setPlayerOnLog(boolean playerOnLog) {
        this.playerOnLog = playerOnLog;
    }

    public void setDifficulty(String difficulty, int numLives) {
        this.difficulty = difficulty;
        this.numLives = numLives;
    }

    public void diePlayer() {
        this.numLives -= 1;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public int getNumLives() {
        return numLives;
    }

    public void decrementLives() {
        this.numLives--;
        if (numLives < 0) {
            setDead();
        }
    }
    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }


    public void setImageResourceId(int id) {
        imageResource = id;
    }
    public int getImageResourceId() {
        return imageResource;
    }

    public void setBoundsLeft(int minSeperation) {
        this.boundsLeft = boundsLeft;
    }

    public void setBoundsRight(int screenWidth, int characterWidth) {
        this.boundsRight = screenWidth - characterWidth;
    }

    public void setBoundsDown(int startTileYPos, int startTileHeight, int characterWidth) {
        //account for soft bar height
        this.boundsDown = (startTileYPos + startTileHeight - characterWidth - 200);
    }
    public void setBoundsTop(int goalTilePosition) {
        this.boundsUp = goalTilePosition;
    }

    public void moveLeft() {
        if (isDead) {
            return;
        }
        posX -= 50.0f;
        if (posX < boundsLeft) {
            posX = boundsLeft;
        }
    }

    public void moveRight() {
        if (isDead) {
            return;
        }
        posX += 50.0f;
        if (posX > boundsRight) {
            posX = boundsRight;
        }
    }

    public boolean isPlayerTouchingSideBoundaries() {
        return (posX > boundsRight) || (posX < boundsLeft);
    }

    public void setDead() {
        isDead = true;
        gameWinStatus = 0; // could have code smell here
    }
    public boolean isDead() {
        return isDead;
    }

    public void moveUp(Vehicle v1, Vehicle v2, Vehicle v3) {
        if (isDead) {
            return;
        }

        posY -= 50.0f;
        if (posY < boundsUp) {
            posY = boundsUp;
        }

        if (v1 != null && v2 != null && v3 != null) {
            double height1 = v1.getPosY();
            double height2 = v2.getPosY();
            double height3 = v3.getPosY();
            if (assertLessThan(posY, height1) && posY < minYPos && !passedVehicle1) {
                minYPos = (int)posY;
                score += 500;
                passedVehicle1 = true;
            } else if (assertLessThan(posY, height2) && posY < minYPos && !passedVehicle2) {
                minYPos = (int)posY;
                score += 1000;
                passedVehicle2 = true;

            } else if (assertLessThan(posY, height3) && posY < minYPos && !passedVehicle3) {
                minYPos = (int)posY;
                score += 2000;
                passedVehicle3 = true;

            } else {
                if (posY < minYPos) {
                    minYPos = (int)posY;
                    score += 5;
                }
            }
        }
    }

    public void moveDown() {
        if (isDead) {
            return;
        }
        posY += 50.0f;
        if (posY > boundsDown) {
            posY = boundsDown;
        }

    }

    public void setPosition(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public void setPosX(float newPosX) {
        posX = newPosX;
    }
    public void setPosY(float newPosY) {
        posY = newPosY;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public int getBoundsUp() {
        return boundsUp;
    }

    public int getScore() {
        return score;
    }

    public void setVehicle1(Vehicle vehicle1) {
        this.vehicle1 = vehicle1;
    }

    public void setVehicle2(Vehicle vehicle2) {
        this.vehicle2 = vehicle2;
    }

    public void setVehicle3(Vehicle vehicle3) {
        this.vehicle3 = vehicle3;
    }
    public Vehicle getVehicle1() {
        return vehicle1;
    }

    public Vehicle getVehicle2() {
        return vehicle2;
    }

    public Vehicle getVehicle3() {
        return vehicle3;
    }

    public boolean assertLessThan(double position, double boundary) {
        if(position < boundary) {
            return true;
        }
        return false;

    }
    public boolean isInCollision() {
        return inCollision;
    }

    public void setInCollision(boolean inCollision) {
        this.inCollision = inCollision;
    }

    public boolean isGoal(int riverAndGoalTileBorderPos) {
        if (this.getPosY() < riverAndGoalTileBorderPos) {
            gameWinStatus = 1;
            score += 5000;
            return true;
        }
        return false;
    }

    public boolean isPlayerDied() {
        return (numLives <= 0);
    }
    public boolean isCollidingWithPlayer(Rect collisionRect) {
        playerRect.left = (int)posX;
        playerRect.right = (int)posX + playerRect.width();
        playerRect.top = (int)posY;
        playerRect.bottom = (int)posY + playerRect.height();
        if (playerRect.intersect(collisionRect)) {
            return true;
        }
        return false;
    }
    public void riverCollisionPenalty() {
        score /= 3;
    }

    public void addVehicleCollisionPenalty() {
        score /= 2;
    }

    public void setScore(int score) {
        this.score = score;
    }



}